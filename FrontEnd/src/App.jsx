import { Routes, Route, Navigate } from "react-router-dom";
import { Dashboard, Auth } from "@/layouts";
import Tables from "@/pages/dashboard/tables";
import FolhaFuncionario from "@/pages/dashboard/FolhaFuncionario";

function App() {
  return (
    <Routes>
      {/* dashboard com rotas filhas */}
      <Route path="/dashboard/*" element={<Dashboard />}>
        <Route index element={<Tables />} /> {/* Página inicial do dashboard */}
        <Route path="folha/:id" element={<FolhaFuncionario />} /> {/* Folha do funcionário */}
      </Route>

      {/* rotas de autenticação */}
      <Route path="/auth/*" element={<Auth />} />

      {/* redirecionamento padrão */}
      <Route path="*" element={<Navigate to="/auth/sign-in" replace />} />
    </Routes>
  );
}

export default App;

