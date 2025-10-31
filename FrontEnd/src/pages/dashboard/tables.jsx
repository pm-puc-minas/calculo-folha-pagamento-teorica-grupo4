import React, { useState } from "react";
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
  Avatar,
  Input,
  Button,
} from "@material-tailwind/react";
import { authorsTableData } from "@/data";

export function Tables() {
  const [search, setSearch] = useState("");

  // Filtro por nome, email ou cargo
  const filteredData = authorsTableData.filter(
    ({ name, email, job }) =>
      name.toLowerCase().includes(search.toLowerCase()) ||
      email.toLowerCase().includes(search.toLowerCase()) ||
      job[0].toLowerCase().includes(search.toLowerCase()) ||
      job[1].toLowerCase().includes(search.toLowerCase())
  );

  // --- Funções de clique ---
  const handleGerarFolha = (funcionario) => {
    console.log(`Gerar folha para: ${funcionario.name}`);
    // Aqui você chamaria seu endpoint do backend:
    // fetch(`http://localhost:8080/folha-pagamento/gerar/${funcionario.id}`, { method: "POST" })
  };

  const handleGerarFolhaTodos = () => {
    console.log("Gerar folha para todos os funcionários");
    // Exemplo: fetch("http://localhost:8080/folha-pagamento/gerar-todos", { method: "POST" })
  };

  return (
    <div className="mt-12 mb-8 flex flex-col gap-12">
      <Card>
        <CardHeader
          variant="gradient"
          color="gray"
          className="mb-8 p-6 flex flex-wrap items-center justify-between gap-4"
        >
          <Typography variant="h6" color="white">
            Funcionários
          </Typography>

          <div className="flex items-center gap-4">
            <div className="w-72">
              <Input
                label="Pesquisar funcionário..."
                color="white"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
            </div>
            <Button
              color="black"
              size="sm"
              onClick={handleGerarFolhaTodos}
              className="whitespace-nowrap"
            >
              Gerar Folha para Todos
            </Button>
          </div>
        </CardHeader>

        <CardBody className="overflow-x-scroll px-0 pt-0 pb-2">
          <table className="w-full min-w-[640px] table-auto">
            <thead>
              <tr>
                {["Nome", "Cargo", "Data de Admissão", "Ações"].map((el) => (
                  <th
                    key={el}
                    className="border-b border-blue-gray-50 py-3 px-5 text-left"
                  >
                    <Typography
                      variant="small"
                      className="text-[11px] font-bold uppercase text-blue-gray-400"
                    >
                      {el}
                    </Typography>
                  </th>
                ))}
              </tr>
            </thead>

            <tbody>
              {filteredData.length > 0 ? (
                filteredData.map(({ img, name, email, job, date }, key) => {
                  const className = `py-3 px-5 ${
                    key === filteredData.length - 1
                      ? ""
                      : "border-b border-blue-gray-50"
                  }`;

                  return (
                    <tr key={name}>
                      {/* Nome */}
                      <td className={className}>
                        <div className="flex items-center gap-4">
                          <Avatar src={img} alt={name} size="sm" variant="rounded" />
                          <div>
                            <Typography
                              variant="small"
                              color="blue-gray"
                              className="font-semibold"
                            >
                              {name}
                            </Typography>
                            <Typography className="text-xs font-normal text-blue-gray-500">
                              {email}
                            </Typography>
                          </div>
                        </div>
                      </td>

                      {/* Cargo */}
                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {job[0]}
                        </Typography>
                        <Typography className="text-xs font-normal text-blue-gray-500">
                          {job[1]}
                        </Typography>
                      </td>

                      {/* Data */}
                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {date}
                        </Typography>
                      </td>

                      {/* Ações */}
                      <td className={className}>
                        <Button
                          size="sm"
                          color="black"
                          onClick={() => handleGerarFolha({ name, email })}
                        >
                          Gerar Folha
                        </Button>
                      </td>
                    </tr>
                  );
                })
              ) : (
                <tr>
                  <td colSpan={4} className="py-4 text-center text-blue-gray-500">
                    Nenhum funcionário encontrado.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </CardBody>
      </Card>
    </div>
  );
}

export default Tables;




