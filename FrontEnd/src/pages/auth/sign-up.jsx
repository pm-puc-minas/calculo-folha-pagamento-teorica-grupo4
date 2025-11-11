import { useState } from "react";
import {
  Input,
  Checkbox,
  Button,
  Typography,
  Select,
  Option,
} from "@material-tailwind/react";

export function SignUp() {
  // máscara de CPF
  const formatCPF = (value) => {
    return value
      .replace(/\D/g, "")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d{1,2})$/, "$1-$2")
      .substring(0, 14);
  };

  const [formData, setFormData] = useState({
    nome: "",
    cpf: "",
    cargo: "",
    salarioBase: "",
    cargaHorariaDiaria: "",
    horasTrabalhadas: "",
    diasTrabalhadasSemana: "",
    diasTrabalhadasMes: "",
    dataAdmissao: "",
    receberValeTransporte: false,
    custoValeTransporte: "",
    receberValeAlimentacao: false,
    custoDiarioAlimentacao: "",
    periculosidade: "NAO",
    insalubridade: "NAO",
    nivelInsalubridade: "",
    dependentes: "",
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    let newValue = type === "checkbox" ? checked : value;
    if (name === "cpf") newValue = formatCPF(value);
    setFormData((prev) => ({ ...prev, [name]: newValue }));
  };

  const handlePericulosidade = (value) => {
    setFormData((prev) => ({
      ...prev,
      periculosidade: value,
      ...(value === "SIM"
        ? { insalubridade: "NAO", nivelInsalubridade: "" }
        : {}),
    }));
  };

  const handleInsalubridade = (value) => {
    setFormData((prev) => ({
      ...prev,
      insalubridade: value,
      ...(value === "SIM" ? { periculosidade: "NAO" } : { nivelInsalubridade: "" }),
    }));
  };

  // ENVIO AO BACKEND COM FETCH ✔
  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      nome: formData.nome,
      cpf: formData.cpf,
      cargo: formData.cargo,
      salarioBase: Number(formData.salarioBase),
      cargaHorariaDiaria: Number(formData.cargaHorariaDiaria),
      horasTrabalhadas: Number(formData.horasTrabalhadas),
      diasTrabalhadasSemana: Number(formData.diasTrabalhadasSemana),
      diasTrabalhadasMes: Number(formData.diasTrabalhadasMes),
      dataAdmissao: formData.dataAdmissao,

      receberValeTransporte: formData.receberValeTransporte,
      custoValeTransporte: formData.receberValeTransporte
        ? Number(formData.custoValeTransporte)
        : null,

      receberValeAlimentacao: formData.receberValeAlimentacao,
      custoDiarioAlimentacao: formData.receberValeAlimentacao
        ? Number(formData.custoDiarioAlimentacao)
        : null,

      periculo: formData.periculosidade === "SIM" ? "SIM" : "NAO",
      insalu: formData.insalubridade === "SIM" ? "SIM" : "NAO",
      insalubridade:
        formData.insalubridade === "SIM"
          ? formData.nivelInsalubridade
          : null,

      dependentes: Number(formData.dependentes),
    };

    try {
      const response = await fetch("http://localhost:8080/funcionarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (!response.ok) throw new Error("Erro ao cadastrar");

      alert("✅ Funcionário cadastrado com sucesso!");
    } catch (error) {
      alert("❌ Erro ao cadastrar. Verifique o console.");
      console.error(error);
    }
  };

  return (
    <section className="m-8 flex">
      <div className="w-2/5 h-full hidden lg:block">
        <img
          src="/img/pattern.png"
          className="h-full w-full object-cover rounded-3xl"
          alt="Cadastro"
        />
      </div>

      <div className="w-full lg:w-3/5 flex flex-col items-center justify-center">
        <Typography variant="h2" className="font-bold mb-4">
          Cadastro de Funcionário
        </Typography>

        <form
          className="mt-8 mb-2 mx-auto w-80 max-w-screen-lg lg:w-1/2"
          onSubmit={handleSubmit}
        >
          <div className="mb-1 flex flex-col gap-6">

            <Input name="nome" label="Nome" size="lg" onChange={handleChange} />

            <Input
              name="cpf"
              label="CPF"
              size="lg"
              value={formData.cpf}
              onChange={handleChange}
            />

            <Input name="cargo" label="Cargo" size="lg" onChange={handleChange} />

            <Input type="number" name="salarioBase" label="Salário Base (R$)" size="lg" onChange={handleChange} />

            <Input type="number" name="cargaHorariaDiaria" label="Carga Horária Diária (h)" size="lg" onChange={handleChange} />

            <Input type="number" name="horasTrabalhadas" label="Horas Trabalhadas" size="lg" onChange={handleChange} />

            <Input type="number" name="diasTrabalhadasSemana" label="Dias Trabalhados por Semana" size="lg" onChange={handleChange} />

            <Input type="number" name="diasTrabalhadasMes" label="Dias Trabalhados por Mês" size="lg" onChange={handleChange} />

            <Input type="date" name="dataAdmissao" label="Data de Admissão" size="lg" onChange={handleChange} />

            <Checkbox name="receberValeTransporte" label="Receber Vale Transporte" checked={formData.receberValeTransporte} onChange={handleChange} />
            {formData.receberValeTransporte && (
              <Input type="number" name="custoValeTransporte" label="Custo Vale Transporte (R$)" size="lg" onChange={handleChange} />
            )}

            <Checkbox name="receberValeAlimentacao" label="Receber Vale Alimentação" checked={formData.receberValeAlimentacao} onChange={handleChange} />
            {formData.receberValeAlimentacao && (
              <Input type="number" name="custoDiarioAlimentacao" label="Custo Diário Alimentação (R$)" size="lg" onChange={handleChange} />
            )}

            <Typography variant="small" className="font-medium">Periculosidade</Typography>
            <Select value={formData.periculosidade} onChange={handlePericulosidade}>
              <Option value="SIM">SIM</Option>
              <Option value="NAO">NÃO</Option>
            </Select>

            <Typography variant="small" className="font-medium">Insalubridade</Typography>
            <Select value={formData.insalubridade} onChange={handleInsalubridade}>
              <Option value="SIM">SIM</Option>
              <Option value="NAO">NÃO</Option>
            </Select>

            {formData.insalubridade === "SIM" && (
              <Select
                value={formData.nivelInsalubridade}
                onChange={(val) => setFormData((prev) => ({ ...prev, nivelInsalubridade: val }))}
                label="Nível de Insalubridade"
              >
                <Option value="ALTO">ALTO</Option>
                <Option value="MEDIO">MÉDIO</Option>
                <Option value="BAIXO">BAIXO</Option>
              </Select>
            )}

            <Input type="number" name="dependentes" label="Dependentes" size="lg" onChange={handleChange} />
          </div>

          <Button type="submit" className="mt-6" fullWidth>
            Cadastrar Funcionário
          </Button>
        </form>
      </div>
    </section>
  );
}

export default SignUp;






