import PropTypes from "prop-types";
import { Typography } from "@material-tailwind/react";
import { BriefcaseIcon } from "@heroicons/react/24/solid";

export function Footer() {
  const year = new Date().getFullYear();

  return (
    <footer className="py-2">
      <div className="flex w-full flex-wrap items-center justify-center px-2">
        <Typography
          variant="small"
          className="font-normal text-inherit text-center flex items-center gap-1"
        >
          &copy; {year} • Sistema de Folha de Pagamento
          <BriefcaseIcon className="inline-block h-4 w-4 text-green-600" />{" "}
          
        </Typography>
      </div>
    </footer>
  );
}

Footer.displayName = "/src/widgets/layout/footer.jsx";

export default Footer;


