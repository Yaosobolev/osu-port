import React from "react";

import { TableCell, TableRow } from "@/components/ui/table";
import { Link } from "react-router-dom";

interface ItemsProps {
  request: {
    id: number;
    arrival_time: string;
    day_of_stay: string;
    serving: string;
    ship: {
      id: number;
      name: string;
      valume: number;
      ship_type: {
        name: string;
      };

      cargo_type: {
        name: string;
      };
    };

    crane: {
      id: number;
      name: string;
      status: string;
      crane_type: {
        id: number;
        name: string;
        speed: number;
      };
    };
    status: string;
  }[]; // Replace YourShipType with the actual type of your ship object
}

const IItems: React.FC<ItemsProps> = ({ request, deleteRequest }) => {
  const arrival_time: string | null = request.arrival_time;
  const time: string | null = arrival_time ? arrival_time.slice(0, 10) : null;

  return (
    <TableRow>
      <TableCell className="font-medium">{request.id}</TableCell>
      <TableCell>{time}</TableCell>
      <TableCell className="text-right">
        {request.ship?.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">
        {request.crane?.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">
        {request.ship?.cargo_type.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">
        {request.ship?.cargo_name || "N/A"}
      </TableCell>
      <TableCell className="text-right">
        {request.ship?.valume || "Разгружено"}
      </TableCell>
      <TableCell className="text-right">
        <Link
          className="text-blue-600 dark:text-blue-500 hover:underline"
          to={`/editShip/${request.id}`}
        >
          Изменить
        </Link>
      </TableCell>
      <TableCell className="text-right">
        <button
          onClick={() => deleteRequest(request.id)}
          className="text-blue-600 dark:text-blue-500 hover:underline"
        >
          Удалить
        </button>
      </TableCell>
    </TableRow>
  );
};

export default IItems;
