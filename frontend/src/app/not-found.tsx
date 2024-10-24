'use client';
import { Button } from "@/components/ui/button";
import { Undo2 } from "lucide-react";
import { useRouter } from "next/navigation";
import Image from "next/image";
import logo from "/public/logo_sem_fundo.png";

export default function Error404() {

    const router = useRouter();

    return (
        <div className="flex flex-col items-center justify-center h-screen w-screen">
            <h1 className="text-principal text-3xl font-bold">Putssss!</h1>
            <h2 className="text-gray-500 md:text-base text-xs mb-4">Parece que a página que está procurando não existe ou foi deletada.</h2>
            <Button variant={"outline"} className="motion-safe:animate-bounce  rounded-full ease-in duration-300 group hover:bg-principal text-principal hover:text-white" onClick={() => router.back()}>
                <Undo2 />
                <span className="hidden group-hover:block ease-in-out delay-100">É melhor voltar</span>
            </Button>
            <Image
                src={logo}
                width={70}
                alt="logo"
                className="mt-8"
            />
            <p className="text-gray-500 font-bold text-xs">Copyright© Polls. 2024 - Todos os direitos reservados.</p>
        </div>
    );
}