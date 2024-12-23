'use client';
import Image from "next/image";
import { PlansInitialPage } from "./plans-page";
import fundo from "/public/planoDeFundoInicial.png";
import mala from "/public/1.png";
import check from "/public/2.png";
import amigos from "/public/3.png";

import { useRouter } from "next/navigation";


export function MainInitialPage() {
    const router = useRouter();

    return (
        <div className="relative">
            <main
                className="h-[600px] w-full bg-cover bg-center"
                style={{
                    backgroundImage: `url(${fundo.src})`,
                }}
            >
                <div className="absolute inset-0 flex p-8 justify-center">
                    <h1 className="text-white md:text-4xl text-2xl text-center font-bold">
                        Crie, <span className="text-green-200">costumize</span> e <span className="text-green-200">compartilhe</span> sua lista de viagem!
                    </h1>
                </div>
                <PlansInitialPage />

                <section className="w-full text-center md:p-4">
                    <h2 className="font-bold md:text-4xl text-2xl text-gray-700" >É muito fácil não esquecer nada!</h2>
                    <div className="grid md:grid-cols-3 grid-cols-1 p-8 gap-8">

                        <div className=" flex flex-col items-center w-full p-4 gap-2 text-center hover:scale-110 transition duration-300">
                            <div className="bg-principal rounded-full">
                                <Image
                                    src={check}
                                    width={120}
                                    alt="checklist-de-viagem"
                                    className="rounded-full p-4"
                                />
                            </div>
                            <span className="font-bold text-gray-700">Organize seu checklist de viagem!</span>
                            <span className="text-center text-xs font-bold text-gray-500">Adicione <span className="font-bold text-green-700">todos os itens</span> necessários<br />que você irá precisar na sua viagem!</span>
                        </div>
                        <div className="flex flex-col items-center p-4 gap-2 text-center hover:scale-110 transition duration-300">
                            <div className="bg-principal rounded-full">
                                <Image
                                    src={mala}
                                    width={120}
                                    alt="mala-de-viagem"
                                    className="p-5"
                                />
                            </div>
                            <span className="font-bold text-gray-700">Nunca esqueça o que levar na mala!</span>
                            <span className="text-center text-xs font-bold text-gray-500">Sem passar aperto, em?<br />Não esqueça <span className="font-bold text-green-700">mais nada </span>para trás!</span>
                        </div>
                        <div className="flex flex-col items-center p-4 gap-2 text-center hover:scale-110 transition duration-300">
                            <div className="bg-principal rounded-full">
                                <Image
                                    src={amigos}
                                    width={120}
                                    alt="compartilhar-viagem"
                                    className="rounded-full p-4"
                                />
                            </div>
                            <span className="font-bold text-gray-700">Compartilhe o checklist com seus amigos!</span>
                            <span className="text-center text-xs font-bold text-gray-500">Enviando o link da sua lista para seus amigos, <br/>eles <span className="font-bold text-green-700">também conseguem adicionar e confirmar</span> os itens!</span>

                        </div>
                    </div>
                </section>

            </main>
        </div>
    );
}