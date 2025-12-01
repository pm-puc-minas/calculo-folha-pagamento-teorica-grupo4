import { useState } from "react";
import {
  Card,
  Input,
  Button,
  Typography,
} from "@material-tailwind/react";
import { useNavigate } from "react-router-dom"; 

export function SignIn() {
  const navigate = useNavigate(); 

  const [formData, setFormData] = useState({
    email: "",
    senha: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      email: formData.email,
      senha: formData.senha,
    };

    try {
      const response = await fetch("http://localhost:8080/admin/logar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        alert("❌ Email ou senha inválidos");
        return;
      }

      const result = await response.text();
      alert(result);

      
      navigate("/dashboard");

    } catch (error) {
      console.error(error);
      alert("Erro ao conectar com o servidor");
    }
  };

  return (
    <section className="m-8 flex gap-4">
      <div className="w-full lg:w-3/5 mt-24">
        <div className="text-center">
          <Typography variant="h2" className="font-bold mb-4">Login</Typography>
          <Typography variant="paragraph" color="blue-gray" className="text-lg font-normal">
            Digite o seu email para realizar o Login.
          </Typography>
        </div>

        <form onSubmit={handleSubmit} className="mt-8 mb-2 mx-auto w-80 max-w-screen-lg lg:w-1/2">
          <div className="mb-1 flex flex-col gap-6">

            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              Seu email
            </Typography>
            <Input
              name="email"
              value={formData.email}
              onChange={handleChange}
              size="lg"
              placeholder="name@mail.com"
            />

            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              Senha
            </Typography>
            <Input
              name="senha"
              type="password"
              value={formData.senha}
              onChange={handleChange}
              size="lg"
              placeholder="********"
            />
          </div>

          <Button type="submit" className="mt-6" fullWidth>
            Logar
          </Button>
        </form>
      </div>

      <div className="w-2/5 h-full hidden lg:block">
        <img
          src="/img/pattern.png"
          className="h-full w-full object-cover rounded-3xl"
        />
      </div>
    </section>
  );
}

export default SignIn;




