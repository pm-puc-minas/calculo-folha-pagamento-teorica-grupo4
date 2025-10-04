import { useState } from "react";
import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
  Select,
  Option,
} from "@material-tailwind/react";

export function SignUp() {
  const [formData, setFormData] = useState({
    cargo: "",
    salarioBase: "",
    cargaHorariaDiaria: "",
    horasTrabalhadas: "",
    diasTrabalhadasSemana: "",
    diasTrabalhadasMes: "",
    dataAdmissao: "",
    receberValeTransporte: false,
    receberValeAlimentacao: false,
    custoValeTransporte: "",
    custoDiarioAlimentacao: "",
    periculosidade: false,
    insalubridade: "NAO_SE_APLICA",
    insalu: "NAO",
    periculo: "NAO",
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Dados do Funcionário:", formData);
    alert("Funcionário cadastrado (dados exibidos no console)");
  };

  return (
    <section className="m-8 flex">
      {/* Lado esquerdo com imagem */}
      <div className="w-2/5 h-full hidden lg:block">
        <img
          src="/img/pattern.png"
          className="h-full w-full object-cover rounded-3xl"
          alt="Cadastro"
        />
      </div>

      {/* Formulário */}
      <div className="w-full lg:w-3/5 flex flex-col items-center justify-center">
        <div className="text-center">
          <Typography variant="h2" className="font-bold mb-4">
            Cadastro de Funcionário
          </Typography>
          <Typography
            variant="paragraph"
            color="blue-gray"
            className="text-lg font-normal"
          >
            Preencha os dados abaixo para cadastrar um novo funcionário.
          </Typography>
        </div>

        <form
          className="mt-8 mb-2 mx-auto w-80 max-w-screen-lg lg:w-1/2"
          onSubmit={handleSubmit}
        >
          <div className="mb-1 flex flex-col gap-6">
            <Input name="cargo" label="Cargo" size="lg" onChange={handleChange} />

            <Input
              type="number"
              name="salarioBase"
              label="Salário Base (R$)"
              size="lg"
              onChange={handleChange}
            />

            <Input
              type="number"
              name="cargaHorariaDiaria"
              label="Carga Horária Diária (h)"
              size="lg"
              onChange={handleChange}
            />

            <Input
              type="number"
              name="horasTrabalhadas"
              label="Horas Trabalhadas"
              size="lg"
              onChange={handleChange}
            />

            <Input
              type="number"
              name="diasTrabalhadasSemana"
              label="Dias Trabalhados por Semana"
              size="lg"
              onChange={handleChange}
            />

            <Input
              type="number"
              name="diasTrabalhadasMes"
              label="Dias Trabalhados por Mês"
              size="lg"
              onChange={handleChange}
            />

            <Input
              type="date"
              name="dataAdmissao"
              label="Data de Admissão"
              size="lg"
              onChange={handleChange}
            />

            {/* Vale Transporte */}
            <Checkbox
              name="receberValeTransporte"
              label="Receber Vale Transporte"
              onChange={handleChange}
            />
            {formData.receberValeTransporte && (
              <Input
                type="number"
                name="custoValeTransporte"
                label="Custo Vale Transporte (R$)"
                size="lg"
                onChange={handleChange}
              />
            )}

            {/* Vale Alimentação */}
            <Checkbox
              name="receberValeAlimentacao"
              label="Receber Vale Alimentação"
              onChange={handleChange}
            />
            {formData.receberValeAlimentacao && (
              <Input
                type="number"
                name="custoDiarioAlimentacao"
                label="Custo Diário Alimentação (R$)"
                size="lg"
                onChange={handleChange}
              />
            )}

            <Checkbox
              name="periculosidade"
              label="Periculosidade"
              onChange={handleChange}
            />

            <div>
              <Typography
                variant="small"
                color="blue-gray"
                className="mb-2 font-medium"
              >
                Insalubridade (nível)
              </Typography>
              <Select
                name="insalubridade"
                value={formData.insalubridade}
                onChange={(val) =>
                  setFormData((prev) => ({ ...prev, insalubridade: val }))
                }
              >
                <Option value="NAO_SE_APLICA">Não se aplica</Option>
                <Option value="BAIXO">Baixo</Option>
                <Option value="MEDIO">Médio</Option>
                <Option value="ALTO">Alto</Option>
              </Select>
            </div>

            <div>
              <Typography
                variant="small"
                color="blue-gray"
                className="mb-2 font-medium"
              >
                Insalubridade (sim/não)
              </Typography>
              <Select
                name="insalu"
                value={formData.insalu}
                onChange={(val) =>
                  setFormData((prev) => ({ ...prev, insalu: val }))
                }
              >
                <Option value="SIM">Sim</Option>
                <Option value="NAO">Não</Option>
              </Select>
            </div>

            <div>
              <Typography
                variant="small"
                color="blue-gray"
                className="mb-2 font-medium"
              >
                Periculosidade (sim/não)
              </Typography>
              <Select
                name="periculo"
                value={formData.periculo}
                onChange={(val) =>
                  setFormData((prev) => ({ ...prev, periculo: val }))
                }
              >
                <Option value="SIM">Sim</Option>
                <Option value="NAO">Não</Option>
              </Select>
            </div>
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



