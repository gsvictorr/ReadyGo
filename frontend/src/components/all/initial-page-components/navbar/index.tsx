'use client';
import Image from "next/image";
import logo from "/public/logo_sem_fundo.png";
import { Button } from "@/components/ui/button";
import { LogInIcon } from "lucide-react";
import { useRouter } from "next/navigation";

export function NavBarInitialPage() {

    const router = useRouter();

    return (
        <header className="flex md:justify-around justify-between p-4 bg-white shadow-md items-center">
            <Image
                src={logo}
                width={100}
                alt="logo"

            />
            <Button className="bg-principal hover:bg-second text-white" 
            onClick={() => router.push("/login")}>
                Entrar <LogInIcon />
            </Button>
        </header>
    );
}