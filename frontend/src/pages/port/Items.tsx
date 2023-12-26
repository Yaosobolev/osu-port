import React from "react";

import { TableCell, TableRow } from "@/components/ui/table";
import { Link } from "react-router-dom";

interface ItemsProps {
  request: {
    id: number;
    arrival_time: string;
    day_of_stay: string;
    new_arrival_time: string;
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
      planned_stay_days: number;
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

const IItems: React.FC<ItemsProps> = ({ request }) => {
  const arrival_time: string | null = request.arrival_time;
  const time: string | null = arrival_time ? arrival_time.slice(0, 10) : null;

  const serving: string | null = request.serving
    ? request.serving.slice(0, 10)
    : null;

  const new_arrival_time: string | null = request.new_arrival_time
    ? request.new_arrival_time.slice(0, 10)
    : null;

  return (
    <TableRow>
      <TableCell className="font-medium">{request.id}</TableCell>
      <TableCell>{time}</TableCell>
      <TableCell>{new_arrival_time}</TableCell>
      <TableCell>{request.day_of_stay}</TableCell>
      <TableCell>{serving}</TableCell>
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
        {request.ship?.valume || "Разгружено"}
      </TableCell>
      <TableCell className="text-right">{request?.status || "N/A"}</TableCell>
    </TableRow>
  );
};

export default IItems;
