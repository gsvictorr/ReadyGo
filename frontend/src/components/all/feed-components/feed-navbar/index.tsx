'use client';
import Image from "next/image";
import logo from "/public/logoComFoto.png";
import { useRouter } from "next/navigation";
import { NavUser } from "./nav-user";
import { Input } from "@/components/ui/input";
import { PlusCircle, Search } from "lucide-react";
import { Button } from "@/components/ui/button";

export function FeedNavBar() {

    const router = useRouter();

    const data = {
        user: {
            name: "Victor",
            email: "victor.silva@grupoguardian.com.br",
            avatar: "https://github.com/gsvictorr.png",
            isPro: false
        },
    }


    return (
        <header className="flex md:justify-center md:gap-32 justify-between mb-6 p-4 py-6 bg-white shadow-md items-center overflow-hidden">
            <div>
            <Image
                src={logo}
                width={100}
                alt="logo"

            />
            </div>
            <div className="flex space-x-4">
                <Input className="md:block md:w-64 hidden" placeholder="Pesquisar">
                </Input>
                <Button variant={"outline"} className="md:hidden"><Search/></Button>
                <Button className="bg-principal hover:bg-second">
                    <PlusCircle/>
                    <span className="hidden md:block">Criar enquete</span>
                    </Button>
            </div>
            <div>
            <NavUser user={data.user} />

            </div>
        </header>
    );
}