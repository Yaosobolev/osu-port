import React from "react";
import { Link } from "react-router-dom";
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

const Request: React.FC = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    loadRequests();
  }, []);

  const loadRequests = async () => {
    const result = await axios.get("http://localhost:8080/requests");
    // console.log(result.data[0].id_product_types.id);
    console.log(result.data);
    setRequests(result.data);
  };

  const deleteRequest = async (id) => {
    await axios.delete(`http://localhost:8080/request/${id}`);
    loadRequests();
  };
  return (
    <div className="container">
      {/* <div className="container mx-auto flex flex-col justify-center items-center text-center h-full"> */}
      <h1 className="text-5xl font-bold text-center mb-12 mt-12">Заявка </h1>
      <Link
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        to={"/addRequest"}
      >
        Добавить заявку
      </Link>
      <Table className="mt-6">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Время прибытия</TableHead>
            <TableHead className="text-right">Название судна</TableHead>
            <TableHead className="text-right">Название крана</TableHead>
            <TableHead className="text-right">Тип груза</TableHead>
            <TableHead className="text-right">Объем груза</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {/* <TableRow> */}
          {requests.map((request, index) => {
            return (
              <Items
                request={request}
                key={index}
                deleteRequest={deleteRequest}
              />
            );
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

export default Request;
