import {
  Card,
  CardBody,
  Avatar,
  Typography,
  Tooltip,
  Button,
} from "@material-tailwind/react";
import { PencilIcon } from "@heroicons/react/24/solid";

export function Profile() {
  const admin = {
    nome: "Gabriel Coelho",
    cargo: "Administrador do Sistema",
    email: "admin@empresa.com",
    telefone: "(31) 99999-0000",
    foto: "/img/admin-avatar.jpeg",
  };

  return (
    <>
      {/* Cabeçalho verde */}
      <div className="relative mt-8 h-48 w-full overflow-hidden rounded-xl bg-black-600">
        <div className="absolute inset-0 flex items-center px-6">
          <Avatar
            src={admin.foto}
            alt={admin.nome}
            size="xl"
            variant="rounded"
            className="border-2 border-white shadow-lg"
          />
          <div className="ml-4">
            <Typography variant="h5" color="white">
              {admin.nome}
            </Typography>
            <Typography variant="small" color="white" className="opacity-90">
              {admin.cargo}
            </Typography>
          </div>
          <div className="ml-auto">
            <Tooltip content="Editar Perfil">
              <Button
                color="white"
                variant="text"
                size="sm"
                className="text-black-800"
              >
                <PencilIcon className="h-4 w-4" />
              </Button>
            </Tooltip>
          </div>
        </div>
      </div>

      {/* Card com informações do admin */}
      <Card className="mx-3 -mt-12 mb-6 lg:mx-4 border border-blue-gray-100 shadow-md">
        <CardBody className="p-6 grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <Typography variant="small" color="blue-gray" className="font-semibold">
              Email:
            </Typography>
            <Typography variant="paragraph" color="gray">
              {admin.email}
            </Typography>
          </div>

          <div>
            <Typography variant="small" color="blue-gray" className="font-semibold">
              Telefone:
            </Typography>
            <Typography variant="paragraph" color="gray">
              {admin.telefone}
            </Typography>
          </div>
        </CardBody>
      </Card>
    </>
  );
}

export default Profile;

