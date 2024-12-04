'use client';
import { PlansInitialPage } from "./plans-page";
import fundo from "/public/planoDeFundoInicial.png";
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
                <PlansInitialPage/>
            </main>
        </div>
    );
}