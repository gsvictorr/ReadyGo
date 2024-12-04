'use client';
import Image from "next/image";
import logo from "/public/logoEscrita.png";
import { Button } from "@/components/ui/button";
import { Facebook, Instagram, Linkedin, LogInIcon, Twitter } from "lucide-react";
import { useRouter } from "next/navigation";
import {
    NavigationMenu,
    NavigationMenuContent,
    NavigationMenuItem,
    NavigationMenuLink,
    NavigationMenuList,
    NavigationMenuTrigger,
    navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu"
import Link from "next/link";


export function NavBarInitialPage() {
    const router = useRouter();

    return (
        <header className="bg-white p-4 py-2 ">

            <div className="flex md:justify-around justify-between items-center">
                <Image
                    src={logo}
                    width={180}
                    alt="logo"
                    className="cursor-pointer"
                />


                <div className="md:flex items-center hidden">
                    <NavigationMenu>
                        <NavigationMenuList>
                            <NavigationMenuItem>
                                <Link href="/" legacyBehavior passHref>
                                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                        Página inicial
                                    </NavigationMenuLink>
                                </Link>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <Link href="/" legacyBehavior passHref>
                                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                        Planos
                                    </NavigationMenuLink>
                                </Link>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <Link href="/" legacyBehavior passHref>
                                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                        Sobre
                                    </NavigationMenuLink>
                                </Link>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <Link href="/" legacyBehavior passHref>
                                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                        Contato
                                    </NavigationMenuLink>
                                </Link>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <Link href="/" legacyBehavior passHref>
                                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                        FAQ/Ajuda
                                    </NavigationMenuLink>
                                </Link>
                            </NavigationMenuItem>
                        </NavigationMenuList>
                    </NavigationMenu>

                        <div className="flex items-center">                            <div className="bg-white p-1 rounded-md">
                                <a href="/"><Instagram size={14} className=" stroke-principal" /></a>
                            </div>
                            <div className="bg-white p-1 rounded-md">
                                <a href="/"><Facebook size={14} className=" stroke-principal" /></a>
                            </div>
                            <div className="bg-white p-1 rounded-md">
                                <a href="/"><Linkedin size={14} className=" stroke-principal" /></a>
                            </div>
                            <div className="bg-white p-1 rounded-md">
                                <a href="/"><Twitter size={14} className=" stroke-principal" /></a>
                            </div>
                        </div>
                </div>
                <Button className="text-white bg-principal py-5 hover:bg-second hover:text-white rounded-full shadow-md" variant={"outline"}
                    onClick={() => router.push("/login")}>
                    Entrar agora <LogInIcon />
                </Button>

            </div>
        </header>
    );
}