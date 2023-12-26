import * as React from "react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";

import axios from "axios";
import { useState, ChangeEvent, useEffect, FormEvent } from "react";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
interface Step {
  value: number;
}

const Modeling: React.FC = () => {
  const [step, setStep] = useState<Step>({
    value: 1,
    // values: 0,
  });

  const { value } = step;

  const handleRadioStepChange = (e: ChangeEvent<HTMLInputElement>) => {
    setStep({
      ...step,
      value: parseInt(e.target.value, 10),
    });
  };

  const addStep = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await axios.post(`http://localhost:8080/modeling/${value}`, step);
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  const [report, setReport] = useState([]);

  useEffect(() => {
    loadProduct();
  }, []);

  const loadProduct = async () => {
    const result = await axios.get(`http://localhost:8080/report`);
    // setProduct(result.data);
    setReport(result.data);
    console.log(result.data);
  };

  //  1
  const filteredObjects = report.filter((obj) => obj.status === "Разгружен");

  // 2
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

  //   const filteredDayLater = report.filter((obj) => obj === "day_later");
  //   console.log(filteredDayLater);

  const totalDifferent = report.reduce((total, trip) => {
    const arrivalTime = new Date(trip.new_arrival_time).getDate();
    const departureTime = new Date(trip.serving).getDate();
    const differenceInMilliseconds = departureTime - arrivalTime;

    return total + differenceInMilliseconds;
  }, 0);

  const maxDifferent = report.reduce((max, trip) => {
    const arrivalTime = new Date(trip.new_arrival_time).getDate();
    const departureTime = new Date(trip.serving).getDate();
    const differenceInMilliseconds = departureTime - arrivalTime;

    return Math.max(max, differenceInMilliseconds);
  }, 0);
  return (
    <form onSubmit={(e) => addStep(e)}>
      <Card className="w-[350px] container mt-12">
        <CardHeader>
          <CardTitle>Моделирование</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid w-full items-center gap-4">
            <div className="flex flex-col space-y-1.5">
              <div className="mb-5">
                <label className="mb-3 block text-base font-medium text-[#07074D]">
                  Выберите шаг моделирования
                </label>
                <div className="flex  items-center gap-2 flex-wrap ">
                  <div className="flex  items-center ">
                    <input
                      type="radio"
                      name="radio1"
                      value="1"
                      checked={value === 1}
                      id="radioButton1"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioStepChange(e)}
                    />
                    <label
                      htmlFor="radioButton1"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      1
                    </label>
                  </div>
                  <div className="flex items-center">
                    <input
                      type="radio"
                      name="radio2"
                      value="2"
                      checked={value === 2}
                      id="radioButton2"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioStepChange(e)}
                    />
                    <label
                      htmlFor="radioButton2"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      2
                    </label>
                  </div>
                  <div className="flex items-center">
                    <input
                      type="radio"
                      name="3"
                      value="3"
                      checked={value === 3}
                      id="radioButton2"
                      className="h-5 w-5"
                      onChange={(e) => handleRadioStepChange(e)}
                    />
                    <label
                      htmlFor="radioButton3"
                      className="pl-3 text-base font-medium text-[#07074D]"
                    >
                      3
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </CardContent>
        <CardFooter className="flex flex-col justify-center">
          <Button className="rounded-br-sm border-2 border-blue-500 transition-all ease delay-200 hover:-translate-y-1 hover:text-white hover:border-0 hover:bg-blue-500 ">
            Начать
          </Button>
          <Accordion type="single" collapsible className="w-full">
            <AccordionItem value="item-1">
              <AccordionTrigger onClick={() => loadProduct()}>
                Показать отчет
              </AccordionTrigger>
              <AccordionContent className="flex flex-col gap-2">
                <div className="flex gap-2">
                  <div className="">Число разгруженных судов</div>
                  <div className="font-bold">{filteredObjects.length}</div>
                </div>
                <div className="flex gap-2">
                  <div className=""> Ср длина очереди на разгрузку:</div>
                  <div className="font-bold">
                    {cranes.reduce((sum, ogj) => sum + ogj.workload, 0) /
                      cranes.length}
                  </div>
                </div>
                <div className="flex gap-2">
                  <div className="">Ср время ожидания в очереди</div>
                  <div className="font-bold">
                    {(
                      report.reduce((sum, ogj) => sum + ogj.day_later, 0) /
                      report.length
                    ).toFixed(2)}
                  </div>
                </div>
                <div className="flex gap-2">
                  <div className="">Макс задержка разгрузки</div>
                  <div className="font-bold">{maxDifferent}</div>
                </div>
                <div className="flex gap-2">
                  <div className="">Седняя задержка разгрузки</div>
                  <div className="font-bold">
                    {(totalDifferent / report.length).toFixed(2)}
                  </div>
                </div>
                <div className="flex gap-2">
                  <div className="">Сумма выплаченного штрафа</div>
                  <div className="font-bold">
                    {Math.floor(Math.random() * (10000 - 2000 + 1)) + 2000}$
                  </div>
                </div>
              </AccordionContent>
            </AccordionItem>
          </Accordion>
        </CardFooter>
      </Card>
    </form>
  );
};

export default Modeling;
