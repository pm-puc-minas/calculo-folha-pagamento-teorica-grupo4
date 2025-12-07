import {
  HomeIcon,
  UserCircleIcon,
  TableCellsIcon,
} from "@heroicons/react/24/solid";
import { Home, Profile, Tables, FolhaFuncionario } from "@/pages/dashboard"; // importar FolhaFuncionario
import { SignIn, SignUp, SignUpAdmin } from "@/pages/auth";

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
        icon: <TableCellsIcon {...icon} />,
        name: "Funcionários",
        path: "/funcionarios",
        element: <Tables />,
      },
      {
        //rota Folha de pagamento
        icon: <TableCellsIcon {...icon} />,
        name: "Folhas de Pagamento",
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
        icon: <HomeIcon {...icon} />,
        name: "sign in",
        path: "/sign-in",
        element: <SignIn />,
      },
      {
        icon: <UserCircleIcon {...icon} />,
        name: "Cadastrar Funcionários",
        path: "/cadastrar_funcionarios",
        element: <SignUp />,
      },

      {
        icon: <UserCircleIcon {...icon} />,
        name: "sign up admin",
        path: "/sign-up-admin",
        element: <SignUpAdmin />,
      },
    ],
  },
];

export default routes;