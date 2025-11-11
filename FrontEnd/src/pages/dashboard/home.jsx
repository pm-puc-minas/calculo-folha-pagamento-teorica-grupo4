import React from "react";
import { Typography, Card, CardBody } from "@material-tailwind/react";
import { StatisticsCard } from "@/widgets/cards";
import { UserGroupIcon, CurrencyDollarIcon, UsersIcon } from "@heroicons/react/24/outline";
import { PieChart, Pie, Cell, Legend, Tooltip } from "recharts";

export function Home() {
  // Dados fictícios
  const totalFuncionarios = 42;
  const totalSalarioBruto = 125000; // fictício
  const totalSalarioLiquido = 98000; // fictício
  const totalDependentes = 15; // fictício

  // Dados para o gráfico de pizza
  const pieData = [
    { name: "Salário Bruto", value: totalSalarioBruto },
    { name: "Salário Líquido", value: totalSalarioLiquido },
    { name: "Dependentes", value: totalDependentes },
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
              Total de funcionários cadastrados no sistema
            </Typography>
          }
        />
        <StatisticsCard
          title="Salário Bruto"
          value={`R$ ${totalSalarioBruto.toLocaleString()}`}
          icon={<CurrencyDollarIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Soma total do salário bruto
            </Typography>
          }
        />
        <StatisticsCard
          title="Salário Líquido"
          value={`R$ ${totalSalarioLiquido.toLocaleString()}`}
          icon={<CurrencyDollarIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Soma total do salário líquido
            </Typography>
          }
        />
        <StatisticsCard
          title="Dependentes"
          value={totalDependentes}
          icon={<UsersIcon className="w-6 h-6 text-white" />}
          footer={
            <Typography className="font-normal text-black-600">
              Total de dependentes
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



