import {
  HomeIcon,
  UserCircleIcon,
  TableCellsIcon,
} from "@heroicons/react/24/solid";
import { Home, Profile, Tables, FolhaFuncionario } from "@/pages/dashboard"; // importar FolhaFuncionario
import { SignIn, SignUp } from "@/pages/auth";

const icon = {
  className: "w-5 h-5 text-inherit",
};

export const routes = [
  {
    layout: "dashboard",
    pages: [
      {
        icon: <HomeIcon {...icon} />,
        name: "Início",
        path: "/home",
        element: <Home />,
      },
      {
        icon: <UserCircleIcon {...icon} />,
        name: "Perfil",
        path: "/profile",
        element: <Profile />,
      },
      {
        icon: <TableCellsIcon {...icon} />,
        name: "Funcionários",
        path: "/funcionarios",
        element: <Tables />,
      },
      {
        //rota Folha de pagamento
        name: "Folha de Pagamento",
        path: "/folha/:id",
        element: <FolhaFuncionario />,
      },
    ],
  },
  {
    title: "Cadastro",
    layout: "auth",
    pages: [
      {
        name: "sign in",
        path: "/sign-in",
        element: <SignIn />,
      },
      {
        name: "Cadastrar Funcionários",
        path: "/cadastrar_funcionarios",
        element: <SignUp />,
      },
    ],
  },
];

export default routes;

