'use client';

import { Button } from "@/components/ui/button";
import { BadgeCheck, BadgeX } from "lucide-react";


export function PlansInitialPage() {

    return (
        <div className="flex flex-col items-center py-36 md:-ml-96 z-0">

            <h2 className="text-white text-2xl z-0">Organize sua viagem <span className="text-green-200 font-bold">PERFEITA!</span> </h2>
            <span className="text-white z-0">comece escolhendo um de nossos planos:</span>
            <div className="text-center flex justify-center gap-4 p-4">
                <div className="p-6 flex flex-col shadow-md
              rounded-lg bg-white z-0 items-center hover:scale-110 ease-in-out duration-300">
                    <h3 className="mb-2 font-bold text-2xl">GRÁTIS</h3>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Criar lista personalizada
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Compartilhar link da lista
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeX size={16} className="text-red-600" /> Adicionar ilimitados itens
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeX size={16} className="text-red-600" /> Ter ilimitadas listas
                    </span>
                    <Button className="mt-2 bg-principal rounded-full hover:bg-second">Ver agora</Button>
                </div>


                <div className=" p-6 flex flex-col shadow-md
              rounded-lg bg-white z-0 items-center hover:scale-110 ease-in-out duration-300">
                    <h3 className="mb-2 font-bold text-2xl text-principal">PREMIUM</h3>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Criar lista personalizada
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Compartilhar link da lista
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Adicionar ilimitados itens
                    </span>
                    <span className="flex items-center gap-1">
                        <BadgeCheck size={16} className="text-green-600" /> Ter ilimitadas listas
                    </span>
                    <Button className="mt-2 bg-principal rounded-full hover:bg-second">Ver agora</Button>
                </div>
            </div>
        </div>
    );
}