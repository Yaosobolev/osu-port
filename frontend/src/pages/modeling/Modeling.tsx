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
  // useEffect(() => {
  //   loadProduct();
  // }, []);

  // const loadProduct = async () => {
  //   const result = await axios.get(`http://localhost:8080/number/${value}`);
  //   setProduct(result.data);
  //   console.log(result.data);
  // };

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
              <AccordionTrigger>Показать отчет</AccordionTrigger>
              <AccordionContent className="flex flex-col gap-2">
                <div className="flex gap-2">
                  <div className="">Число разгруженных судов</div>
                  <div className="font-bold"></div>
                </div>
                <div className="flex gap-2">
                  <div className=""> Ср длина очереди на разгрузку:</div>
                  <div className="font-bold"></div>
                </div>
                <div className="flex gap-2">
                  <div className="">Ср время ожидания в очереди</div>
                  <div className="font-bold"></div>
                </div>
                <div className="flex gap-2">
                  <div className="">Макс задержка разгрузки</div>
                  <div className="font-bold"></div>
                </div>
                <div className="flex gap-2">
                  <div className="">Седняя задержка разгрузки</div>
                  <div className="font-bold"></div>
                </div>
                <div className="flex gap-2">
                  <div className="">Сумма выплаченного штрафа</div>
                  <div className="font-bold"></div>
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
