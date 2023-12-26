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
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, ChangeEvent, FormEvent } from "react";
interface Ship {
  name: string;
  weight: number;
  ship_type: string;
  cargo_name: string;
  valume: number;
  cargo_type: string;
  planned_stay_days: number;
}

const AddBtn: React.FC = () => {
  const navigate = useNavigate();

  const [ship, setShip] = useState<Ship>({
    name: "",
    weight: 0,
    ship_type: "1",
    cargo_name: "",
    valume: 0,
    cargo_type: "1",
    planned_stay_days: 0,
  });

  const { name, weight, ship_type, cargo_name, valume } = ship;

  const onInputShipChange = (e: ChangeEvent<HTMLInputElement>) => {
    setShip({
      ...ship,
      [e.target.name]: e.target.value,
      planned_stay_days: valume / 2,
    });
    // console.log(ship);
  };

  const handleRadioShipChange = (e: ChangeEvent<HTMLInputElement>) => {
    setShip({
      ...ship,
      ship_type: e.target.value,
      cargo_type: e.target.value,
    });
  };

  const addShip = async (e: FormEvent) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8080/ship", ship);
      navigate("/");
    } catch (error) {
      if (valume > weight) {
        alert("Вес груза не может превышать грузоподъемность судна");
      }
      console.error("Error adding product:", error);
    }
  };

  return (
    <form onSubmit={(e) => addShip(e)}>
      <Card className="w-[350px] container mt-12">
        <CardHeader>
          <CardTitle>Добавление судна</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid w-full items-center gap-4">
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">Название</Label>
              <Input
                id="name"
                name="name"
                value={name}
                placeholder="Напишите название"
                onChange={(e) => onInputShipChange(e)}
              />
            </div>
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">Грузоподъемность</Label>
              <Input
                type="number"
                id="name"
                name="weight"
                placeholder="Напишите объем"
                value={weight}
                onChange={(e) => onInputShipChange(e)}
              />
            </div>
            <div className="flex flex-col space-y-1.5">
              <div className="mb-5">
                <label className="mb-3 block text-base font-medium text-[#07074D]">
                  Выберите тип судна
                </label>
                <div className="flex items-center  gap-2 flex-wrap ">
                  <div className="flex  items-center ">
                    <input
                      type="radio"
                      name="radio1"
                      value="1"
                      checked={ship_type === "1"}
                      id="radioButton1"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioShipChange(e)}
                    />
                    <label
                      htmlFor="radioButton1"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      Сыпучий
                    </label>
                  </div>
                  <div className="flex items-center">
                    <input
                      type="radio"
                      name="radio2"
                      value="2"
                      checked={ship_type === "2"}
                      id="radioButton2"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioShipChange(e)}
                    />
                    <label
                      htmlFor="radioButton2"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      Жидкий
                    </label>
                  </div>
                  <div className="flex items-center">
                    <input
                      type="radio"
                      name="3"
                      value="3"
                      checked={ship_type === "3"}
                      id="radioButton2"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioShipChange(e)}
                    />
                    <label
                      htmlFor="radioButton3"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      Контейнер
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">Название груза</Label>
              <Input
                id="name"
                name="cargo_name"
                placeholder="Напишите название"
                value={cargo_name}
                onChange={(e) => onInputShipChange(e)}
              />
            </div>
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">Объем груза</Label>
              <Input
                type="number"
                id="name"
                name="valume"
                placeholder="Напишите объем"
                value={valume}
                onChange={(e) => onInputShipChange(e)}
              />
            </div>
          </div>
        </CardContent>
        <CardFooter className="flex justify-between">
          <Link className="outline px-2 py-1" to={"/"}>
            Назад
          </Link>
          <Button>Добавить</Button>
        </CardFooter>
      </Card>
    </form>
  );
};

export default AddBtn;
