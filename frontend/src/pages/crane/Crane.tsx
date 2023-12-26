import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";

("use client");

import {
  Table,
  TableBody,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import Items from "./Items";

const Crane: React.FC = () => {
  const [cranes, setCranes] = useState([]);
  useEffect(() => {
    loadCranes();
  }, []);

  const loadCranes = async () => {
    const result = await axios.get("http://localhost:8080/cranes");
    console.log(result.data);
    setCranes(result.data);
  };

  return (
    <div className="container">
      <h1 className="text-5xl font-bold text-center mb-12 mt-12">Краны </h1>

      <Table className="mt-6">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Название</TableHead>
            <TableHead>Тип крана</TableHead>
            <TableHead className="text-right">Скорость разгрузки</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {cranes.map((crane, index) => {
            return <Items crane={crane} key={index} />;
          })}
        </TableBody>
      </Table>
    </div>
  );
};

export default Crane;
