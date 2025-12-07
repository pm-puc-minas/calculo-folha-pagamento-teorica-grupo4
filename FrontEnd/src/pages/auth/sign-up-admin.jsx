import { useState } from "react";
import {
  Card,
  Input,
  Button,
  Typography,
} from "@material-tailwind/react";

export function SignUpAdmin() {
  const [formData, setFormData] = useState({
    nome: "",
    cpf: "",
    email: "",
    senha: "",
    confirmarSenha: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const formatCPF = (cpf) => {
    const cleaned = cpf.replace(/\D/g, "");
    if (cleaned.length <= 11) {
      return cleaned
        .replace(/(\d{3})(\d)/, "$1.$2")
        .replace(/(\d{3})(\d)/, "$1.$2")
        .replace(/(\d{3})(\d{2})$/, "$1-$2");
    }
    return cpf;
  };

  const handleCPFChange = (e) => {
    const value = e.target.value;
    setFormData((prev) => ({
      ...prev,
      cpf: formatCPF(value),
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.senha !== formData.confirmarSenha) {
      alert("As senhas não coincidem!");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/admin/registrar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nome: formData.nome,
          cpf: formData.cpf.replace(/\D/g, ""), // envia CPF sem máscara
          email: formData.email,
          senha: formData.senha,
        }),
      });

      if (!response.ok) {
        throw new Error("Erro ao cadastrar admin");
      }

      alert("Administrador cadastrado com sucesso!");
      setFormData({
        nome: "",
        cpf: "",
        email: "",
        senha: "",
        confirmarSenha: "",
      });

    } catch (error) {
      console.error(error);
      alert("Erro ao cadastrar administrador.");
    }
  };

  return (
    <section className="m-8 flex">
      <div className="w-2/5 h-full hidden lg:block">
        <img
          src="/img/pattern.png"
          className="h-full w-full object-cover rounded-3xl"
          alt="Cadastro de Admin"
        />
      </div>

      <div className="w-full lg:w-3/5 flex flex-col items-center justify-center">
        <div className="text-center">
          <Typography variant="h2" className="font-bold mb-4">
            Cadastro de Administrador
          </Typography>
          <Typography
            variant="paragraph"
            color="blue-gray"
            className="text-lg font-normal"
          >
            Preencha os dados abaixo para cadastrar um novo administrador.
          </Typography>
        </div>

        <form
          className="mt-8 mb-2 mx-auto w-80 max-w-screen-lg lg:w-1/2"
          onSubmit={handleSubmit}
        >
          <div className="mb-1 flex flex-col gap-6">
            <Input
              name="nome"
              label="Nome Completo"
              size="lg"
              value={formData.nome}
              onChange={handleChange}
            />

            <Input
              type="text"
              name="cpf"
              label="CPF"
              size="lg"
              value={formData.cpf}
              onChange={handleCPFChange}
              placeholder="000.000.000-00"
              maxLength="14"
            />

            <Input
              type="email"
              name="email"
              label="Email"
              size="lg"
              value={formData.email}
              onChange={handleChange}
            />

            <Input
              type="password"
              name="senha"
              label="Senha"
              size="lg"
              value={formData.senha}
              onChange={handleChange}
            />

            <Input
              type="password"
              name="confirmarSenha"
              label="Confirmar Senha"
              size="lg"
              value={formData.confirmarSenha}
              onChange={handleChange}
            />
          </div>

          <Button type="submit" className="mt-6" fullWidth>
            Cadastrar Administrador
          </Button>
        </form>
      </div>
    </section>
  );
}

export default SignUpAdmin;

