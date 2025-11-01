import React, { useState, useEffect } from "react";
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
  Input,
  Button,
  Avatar,
} from "@material-tailwind/react";

export function Tables() {
  const [funcionarios, setFuncionarios] = useState([]);
  const [search, setSearch] = useState("");


  useEffect(() => {
    fetch("http://localhost:8080/funcionarios/mostrarCampos")
      .then((res) => res.json())
      .then((data) => setFuncionarios(data))
      .catch((err) => console.error("Erro ao buscar funcionários:", err));
  }, []);

  // Filtro por nome ou cargo
  const filteredData = funcionarios.filter(
    (f) =>
      f.nome?.toLowerCase().includes(search.toLowerCase()) ||
      f.cargo?.toLowerCase().includes(search.toLowerCase())
  );

  const handleGerarFolha = (funcionario) => {
    console.log(`Gerar folha para: ${funcionario.nome}`);
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
              color="blue" 
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
                filteredData.map((f, key) => {
                  const className = `py-3 px-5 ${
                    key === filteredData.length - 1
                      ? ""
                      : "border-b border-blue-gray-50"
                  }`;

                  return (
                    <tr key={f.id}>
                      {/* Nome */}
                      <td className={className}>
                        <div className="flex items-center gap-4">
                          <Avatar
                            src={f.img || "https://via.placeholder.com/40"}
                            alt={f.nome}
                            size="sm"
                            variant="rounded"
                          />
                          <div>
                            <Typography
                              variant="small"
                              color="blue-gray"
                              className="font-semibold"
                            >
                              {f.nome}
                            </Typography>
                            <Typography className="text-xs font-normal text-blue-gray-500">
                              {f.email || "—"}
                            </Typography>
                          </div>
                        </div>
                      </td>

                      {/* Cargo */}
                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {f.cargo || "—"}
                        </Typography>
                      </td>

                      {/* Data de Admissão */}
                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {f.dataAdmissao || "—"}
                        </Typography>
                      </td>

                      {/* Ações */}
                      <td className={className}>
                        <Button
                          size="sm"
                          color="blue"
                          onClick={() => handleGerarFolha(f)}
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





