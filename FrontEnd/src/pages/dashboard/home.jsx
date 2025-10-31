import React from "react";
import { Typography } from "@material-tailwind/react";
import { StatisticsCard } from "@/widgets/cards";
import { UserGroupIcon } from "@heroicons/react/24/outline";

export function Home() {
  const totalFuncionarios = 42; // depois você puxa do backend

  return (
    <div className="mt-12">
      <div className="grid gap-y-10 gap-x-6 md:grid-cols-2 xl:grid-cols-4">
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
      </div>
    </div>
  );
}

export default Home;

