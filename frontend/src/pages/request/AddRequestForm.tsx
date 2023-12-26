"use client";
import * as React from "react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Calendar } from "@/components/ui/calendar";
// import {
//   Select,
//   SelectContent,
//   SelectItem,
//   SelectTrigger,
//   SelectValue,
// } from "@/components/ui/select";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, ChangeEvent, FormEvent, useEffect } from "react";

interface Request {
  id: number;
  arrival: string;
}

const AddRequestForm: React.FC = () => {
  const navigate = useNavigate();

  const [request, setRequest] = useState<Request>({
    id: 0,
    arrival: "",
  });

  const { id, arrival } = request;

  const onInputRequestChange = (e: ChangeEvent<HTMLInputElement>) => {
    setRequest({
      ...request,
      [e.target.name]: e.target.value,
    });
  };

  const addRequest = async (e: FormEvent) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8080/request", request);
      navigate("/request");
      // Handle success or additional logic here if needed
    } catch (error) {
      // Handle error
      console.error("Error adding product:", error);
    }
  };

  const [date, setDate] = React.useState<Date | undefined>(new Date());
  useEffect(() => {
    setRequest({
      ...request,
      arrival: date,
      // date &&
      // `${date.getFullYear()}-${(date.getMonth() + 1)
      //   .toString()
      //   .padStart(2, "0")}-${date.getDate().toString().padStart(2, "0")}`,
    });
  }, [date]);

  return (
    <form onSubmit={(e) => addRequest(e)}>
      <Card className="w-[350px] container mt-12">
        <CardHeader>
          <CardTitle>Добавление заявки</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid w-full items-center gap-4">
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">Выберите судно</Label>
              <Input
                id="name"
                name="id"
                value={id}
                placeholder="Напишите название"
                onChange={(e) => onInputRequestChange(e)}
                type="number"
              />
            </div>

            <Calendar
              mode="single"
              selected={date}
              onSelect={setDate}
              className="rounded-md border"
            />
          </div>
        </CardContent>
        <CardFooter className="flex justify-between">
          <Link className="outline px-2 py-1" to={"/request"}>
            Назад
          </Link>
          <Button>Добавить</Button>
        </CardFooter>
      </Card>
    </form>
  );
};

export default AddRequestForm;
