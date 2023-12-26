import React from "react";

import { TableCell, TableRow } from "@/components/ui/table";
import { Link } from "react-router-dom";

interface IItemsProps {
  ship: {
    id: number;
    name: string;
    pLanned_stay_days: number;
    ship_type: {
      id: number;
      name: string;
    };
    weight: number;
  }[]; // Replace YourShipType with the actual type of your ship object
}

const IItems: React.FC<IItemsProps> = ({ ship, deleteShip }) => {
  return (
    <TableRow>
      <TableCell className="font-medium">{ship.id}</TableCell>
      <TableCell>{ship.name}</TableCell>
      <TableCell>{ship.weight}</TableCell>
      <TableCell className="text-right">
        {ship.ship_type?.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">{ship.cargo_name}</TableCell>
      <TableCell className="text-right">
        {ship.cargo_type?.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">{ship.valume}</TableCell>
      <TableCell className="text-right">
        <Link
          className="text-blue-600 dark:text-blue-500 hover:underline"
          to={`/editShip/${ship.id}`}
        >
          Изменить
        </Link>
      </TableCell>
      <TableCell className="text-right">
        <button
          onClick={() => deleteShip(ship.id)}
          className="text-blue-600 dark:text-blue-500 hover:underline"
        >
          Удалить
        </button>
      </TableCell>
    </TableRow>
  );
};

export default IItems;
