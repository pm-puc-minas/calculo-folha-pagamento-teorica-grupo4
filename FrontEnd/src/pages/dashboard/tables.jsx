import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
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
  const navigate = useNavigate();

  // Buscar funcionários
  const carregarFuncionarios = () => {
    fetch("http://localhost:8080/funcionarios/mostrarCampos")
      .then((res) => res.json())
      .then((data) => setFuncionarios(data))
      .catch((err) => console.error("Erro ao buscar funcionários:", err));
  };

  useEffect(() => {
    carregarFuncionarios();
  }, []);

  const filteredData = funcionarios.filter(
    (f) =>
      f.nome?.toLowerCase().includes(search.toLowerCase()) ||
      f.cargo?.toLowerCase().includes(search.toLowerCase())
  );

  // Gerar folha
  const handleGerarFolha = async (funcionario) => {
    try {
      const gerarResponse = await fetch(
        `http://localhost:8080/folha-pagamento/gerar/${funcionario.idFuncionario}`,
        { method: "POST" }
      );

      if (!gerarResponse.ok) {
        alert("Erro ao gerar folha de pagamento.");
        return;
      }

      carregarFuncionarios();
    } catch (error) {
      console.error(error);
      alert("Erro ao conectar com o servidor");
    }
  };

  // Ver folha
  const verFolha = (idFuncionario) => {
    navigate(`/dashboard/folha/${idFuncionario}`);
  };

  // Deletar funcionário
  const handleDeletarFuncionario = async (idFuncionario) => {
    if (!window.confirm("Tem certeza que deseja deletar este funcionário?")) return;

    try {
      const response = await fetch(
        `http://localhost:8080/funcionarios/deletarFuncionario/${idFuncionario}`,
        { method: "DELETE" }
      );

      if (!response.ok) {
        alert("É preciso deletar a folha primeiro, para que o funcionário seja deletado!!");
        return;
      }

      alert("Funcionário deletado com sucesso!");
      carregarFuncionarios();
    } catch (error) {
      console.error(error);
      alert("Erro ao conectar com o servidor");
    }
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
                filteredData.map((f) => {
                  const className = "py-3 px-5 border-b border-blue-gray-50";

                  return (
                    <tr key={f.idFuncionario}>
                      <td className={className}>
                        <div className="flex items-center gap-4">
                          <Avatar
                            src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                            alt={f.nome}
                            size="sm"
                            variant="rounded"
                          />
                          <Typography
                            variant="small"
                            color="blue-gray"
                            className="font-semibold"
                          >
                            {f.nome}
                          </Typography>
                        </div>
                      </td>

                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {f.cargo || "—"}
                        </Typography>
                      </td>

                      <td className={className}>
                        <Typography className="text-xs font-semibold text-blue-gray-600">
                          {f.dataAdmissao || "—"}
                        </Typography>
                      </td>

                      <td className={`${className} flex gap-2`}>
                        {f.possuiFolha ? (
                          <Button
                            size="sm"
                            color="green"
                            onClick={() => verFolha(f.idFuncionario)}
                          >
                            Ver Folha
                          </Button>
                        ) : (
                          <Button
                            size="sm"
                            color="blue"
                            onClick={() => handleGerarFolha(f)}
                          >
                            Gerar Folha
                          </Button>
                        )}

                        <Button
                          size="sm"
                          color="red"
                          onClick={() => handleDeletarFuncionario(f.idFuncionario)}
                        >
                          Deletar
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















