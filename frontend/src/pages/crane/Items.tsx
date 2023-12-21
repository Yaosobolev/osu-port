import React from "react";

import { TableCell, TableRow } from "@/components/ui/table";
import { Link } from "react-router-dom";

interface ItemsProps {
  crane: {
    id: number;
    name: string;
    crane_type: {
      id: number;
      name: string;
      speed: number;
    };
  }[]; // Replace YourShipType with the actual type of your ship object
}

const Items: React.FC<ItemsProps> = ({ crane }) => {
  return (
    <TableRow>
      <TableCell className="font-medium">{crane.id}</TableCell>
      <TableCell>{crane.name}</TableCell>
      <TableCell className="text-left">
        {crane.crane_type?.name || "N/A"}
      </TableCell>
      <TableCell className="text-right">
        {crane.crane_type?.speed || "N/A"}
      </TableCell>
    </TableRow>
  );
};

export default Items;
