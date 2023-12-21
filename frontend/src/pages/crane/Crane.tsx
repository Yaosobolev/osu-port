import React from "react";
import { Link, useParams } from "react-router-dom";
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
// import IItems from "../ship/IItems";
import Items from "./Items";

const Crane: React.FC = () => {
  const [cranes, setCranes] = useState([]);
  useEffect(() => {
    loadCranes();
  }, []);

  const loadCranes = async () => {
    const result = await axios.get("http://localhost:8080/cranes");
    // console.log(result.data[0].id_product_types.id);
    console.log(result.data);
    setCranes(result.data);
  };

  return (
    <div className="container">
      {/* <div className="container mx-auto flex flex-col justify-center items-center text-center h-full"> */}
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
          {/* <TableRow> */}
          {cranes.map((crane, index) => {
            return <Items crane={crane} key={index} />;
          })}
          {/* <TableCell className="font-medium">INV001</TableCell>
            <TableCell>Paid</TableCell>
            <TableCell>Credit Card</TableCell>
            <TableCell className="text-right">$250.00</TableCell>
            <TableCell className="text-right">$250.00</TableCell>
            <TableCell className="text-right">$250.00</TableCell>
            <TableCell className="text-right">$250.00</TableCell> */}
          {/* </TableRow> */}
        </TableBody>
      </Table>
      {/* </div> */}
    </div>
  );
};

export default Crane;
