import React, { useState, useEffect } from "react";
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
  Input,
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
} from "@material-tailwind/react";

export function FolhaFuncionario() {
  const [folhas, setFolhas] = useState([]);
  const [search, setSearch] = useState("");
  const [folhaSelecionada, setFolhaSelecionada] = useState(null); // modal

  useEffect(() => {
    const fetchFolhas = async () => {
      try {
        const res = await fetch("http://localhost:8080/folha-pagamento/listar");
        const data = await res.json();
        setFolhas(data);
      } catch (error) {
        console.error("Erro ao buscar folhas:", error);
      }
    };

    fetchFolhas();
  }, []);

  // Filtrar folhas pelo nome do funcionário
  const filteredFolhas = folhas.filter((f) =>
    f.funcionario.nome.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="mt-12 mb-8 flex flex-col gap-12">
      <Card>
        <CardHeader
          variant="gradient"
          color="gray"
          className="mb-8 p-6 flex flex-wrap items-center justify-between gap-4"
        >
          <Typography variant="h6" color="white">
            Folhas Geradas
          </Typography>

          <div className="w-72">
            <Input
              label="Buscar funcionário..."
              color="white"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
        </CardHeader>

        <CardBody className="overflow-x-scroll px-0 pt-0 pb-2">
          <table className="w-full min-w-[640px] table-auto">
            <thead>
              <tr>
                {["Nome", "Cargo", "Salário Bruto", "Salário Líquido", "Data", "Ações"].map((el) => (
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
              {filteredFolhas.length > 0 ? (
                filteredFolhas.map((f) => (
                  <tr key={f.idFolhaPagamento}>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      {f.funcionario.nome}
                    </td>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      {f.funcionario.cargo}
                    </td>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      R$ {f.salarioBruto.toFixed(2)}
                    </td>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      R$ {f.salarioLiquido.toFixed(2)}
                    </td>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      {f.geracaoData}
                    </td>
                    <td className="py-3 px-5 border-b border-blue-gray-50">
                      <Button
                        size="sm"
                        color="green"
                        onClick={() => setFolhaSelecionada(f)}
                      >
                        Ver Detalhes
                      </Button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={6} className="py-4 text-center text-blue-gray-500">
                    Nenhuma folha encontrada.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </CardBody>
      </Card>

      {/* MODAL */}
      <Dialog open={!!folhaSelecionada} handler={() => setFolhaSelecionada(null)}>
        <DialogHeader>Detalhes da Folha</DialogHeader>
        <DialogBody divider className="space-y-2">
          {folhaSelecionada && (
            <>
              <Typography><strong>Nome:</strong> {folhaSelecionada.funcionario.nome}</Typography>
              <Typography><strong>CPF:</strong> {folhaSelecionada.funcionario.cpf}</Typography>
              <Typography><strong>Cargo:</strong> {folhaSelecionada.funcionario.cargo}</Typography>
              <Typography><strong>Salário Base:</strong> R$ {folhaSelecionada.salarioBase.toFixed(2)}</Typography>
              <Typography><strong>Salário Bruto:</strong> R$ {folhaSelecionada.salarioBruto.toFixed(2)}</Typography>
              <Typography><strong>Salário Líquido:</strong> R$ {folhaSelecionada.salarioLiquido.toFixed(2)}</Typography>
              <Typography><strong>FGTS:</strong> R$ {folhaSelecionada.fgts.toFixed(2)}</Typography>
              <Typography><strong>INSS:</strong> R$ {folhaSelecionada.inss.toFixed(2)}</Typography>
              <Typography><strong>IRRF:</strong> R$ {folhaSelecionada.irrf.toFixed(2)}</Typography>
              <Typography><strong>Vale Transporte:</strong> R$ {folhaSelecionada.vt.toFixed(2)}</Typography>
              <Typography><strong>Vale Alimentação:</strong> R$ {folhaSelecionada.va.toFixed(2)}</Typography>
              <Typography><strong>Data da geração:</strong> {folhaSelecionada.geracaoData}</Typography>
            </>
          )}

          <div className="flex justify-end pt-4">
            <Button color="red" onClick={() => setFolhaSelecionada(null)}>
              Fechar
            </Button>
          </div>
        </DialogBody>
      </Dialog>
    </div>
  );
}

export default FolhaFuncionario;


