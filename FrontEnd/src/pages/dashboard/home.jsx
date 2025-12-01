import React, { useEffect, useState } from "react";
import { Typography, Card, CardBody } from "@material-tailwind/react";
import { StatisticsCard } from "@/widgets/cards";
import { UserGroupIcon, CurrencyDollarIcon } from "@heroicons/react/24/outline";
import { PieChart, Pie, Cell, Legend, Tooltip } from "recharts";

export function Home() {
  // Estados para armazenar dados vindos dos endpoints
  const [totalFuncionarios, setTotalFuncionarios] = useState(0);
  const [totalSalarioBruto, setTotalSalarioBruto] = useState(0);
  const [totalSalarioLiquido, setTotalSalarioLiquido] = useState(0);
  const [mediaSalarioLiquido, setMediaSalarioLiquido] = useState(0);

  // Carregar dados dos endpoints ao montar o componente
  useEffect(() => {
    async function fetchData() {
      try {
        const respFunc = await fetch("http://localhost:8080/folha-pagamento/TotalFuncionario");
        const respBruto = await fetch("http://localhost:8080/folha-pagamento/TotalBruto");
        const respLiquido = await fetch("http://localhost:8080/folha-pagamento/TotalLiquido");
        const respMedia = await fetch("http://localhost:8080/folha-pagamento/media");

        setTotalFuncionarios(await respFunc.json());
        setTotalSalarioBruto(await respBruto.json());
        setTotalSalarioLiquido(await respLiquido.json());
        setMediaSalarioLiquido(await respMedia.json());
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    }
    fetchData();
  }, []);

  // gráfico de pizza
  const pieData = [
    { name: "Salário Bruto", value: totalSalarioBruto },
    { name: "Salário Líquido", value: totalSalarioLiquido },
    { name: "Média Salário Líquido", value: mediaSalarioLiquido },
  ];

  const COLORS = ["#0088FE", "#00C49F", "#FFBB28"];

  return (
    <div className="mt-12">
      {/* Estatísticas principais */}
      <div className="grid gap-y-10 gap-x-6 md:grid-cols-2 xl:grid-cols-4 mb-12">
        <StatisticsCard
          title="Funcionários"
          value={totalFuncionarios}
          icon={<UserGroupIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Total de funcionários cadastrados
            </Typography>
          }
        />
        <StatisticsCard
          title="Salário Bruto"
          value={`R$ ${totalSalarioBruto.toLocaleString()}`}
          icon={<CurrencyDollarIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Soma total dos salários brutos
            </Typography>
          }
        />
        <StatisticsCard
          title="Salário Líquido"
          value={`R$ ${totalSalarioLiquido.toLocaleString()}`}
          icon={<CurrencyDollarIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Soma total dos salários líquidos
            </Typography>
          }
        />
        <StatisticsCard
          title="Média Salário Líquido"
          value={`R$ ${mediaSalarioLiquido.toLocaleString()}`}
          icon={<CurrencyDollarIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Média geral dos salários líquidos
            </Typography>
          }
        />
      </div>

      {/* Gráfico de Pizza */}
      <Card className="w-full md:w-1/2 mx-auto">
        <CardBody>
          <Typography variant="h6" color="blue-gray" className="mb-4">
            Distribuição Financeira
          </Typography>
          <PieChart width={400} height={300}>
            <Pie
              data={pieData}
              cx="50%"
              cy="50%"
              outerRadius={100}
              fill="#8884d8"
              dataKey="value"
              label
            >
              {pieData.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </CardBody>
      </Card>
    </div>
  );
}

export default Home;




