'use client';

import Image from "next/image";
import logo from "/public/logoComFoto.png";
import { useForm } from "react-hook-form";
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { Label } from "@radix-ui/react-label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Loader2 } from "lucide-react";
import { useState } from "react";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRouter } from "next/navigation";


const registerFormSchema = z.object({
    username: z.string().min(3, { message: "Insira um nome de usuário com pelo menos 3 caracteres." }),
    email: z.string().email({ message: "Insira um email válido." }).min(10, { message: "Insira um email com no mínimo 10 caracteres." }),
    password: z.string().min(8, { message: "Insira uma senha com no mínimo 8 caracteres." }),
    repeat_password: z.string().min(8, { message: "Insira uma senha com no mínimo 8 caracteres." }),
});

type RegisterFormType = z.infer<typeof registerFormSchema>;


export function RegisterForm() {
    const [loading, setLoading] = useState(false);
    const router = useRouter();
    const registerForm = useForm<RegisterFormType>({
        resolver: zodResolver(registerFormSchema),
        defaultValues: {
            username: "",
            email: "",
            password: "",
            repeat_password: ""
        }
    });


    return (
        <>
            <div className="flex items-center justify-center h-screen">
                <div className="container shadow-md space-y-2 p-8 max-w-xs rounded-md border border-1 bg-white dark:bg-zinc-950">
                    <div className="flex flex-col justify-center w-full gap-4">
                        <div className="flex justify-center">
                            <Image
                                src={logo}
                                width={60}
                                height={60}
                                alt="logo"
                            />
                        </div>
                        <span className="text-gray-500 -mt-2 text-center">Seu criador de enquetes!</span>
                        <h1 className="font-bold text-gray-500 text-2xl dark:text-white">Cadastre-se</h1>

                        <Form {...registerForm}>
                            <form className="space-y-4 w-full">
                                <FormField
                                    control={registerForm.control}
                                    name="username"
                                    render={({ field }) => (
                                        <FormItem className="mb-2">
                                            <Label htmlFor='text' className='font-bold text-sm'>Nome de usuário</Label>
                                            <FormControl>
                                                <Input
                                                    type='text'
                                                    placeholder='@exemplo'
                                                    className='rounded-md border-l-4 border-l-principal dark:bg-zinc-950'
                                                    {...field}
                                                />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />
                                <FormField
                                    control={registerForm.control}
                                    name="email"
                                    render={({ field }) => (
                                        <FormItem className="mb-2">
                                            <Label htmlFor='text' className='font-bold text-sm'>Email</Label>
                                            <FormControl>
                                                <Input
                                                    type='text'
                                                    placeholder='exemplo@email.com'
                                                    className='rounded-md border-l-4 border-l-principal dark:bg-zinc-950'
                                                    {...field}
                                                />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />
                                <FormField
                                    control={registerForm.control}
                                    name="password"
                                    render={({ field }) => (
                                        <FormItem>
                                            <Label htmlFor='password' className='font-bold text-sm'>Senha</Label>
                                            <FormControl>
                                                <Input
                                                    type='password'
                                                    placeholder='Digite sua senha'
                                                    className='rounded-md border-l-4 border-l-principal dark:bg-zinc-950'
                                                    {...field}
                                                />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />
                                                                <FormField
                                    control={registerForm.control}
                                    name="repeat_password"
                                    render={({ field }) => (
                                        <FormItem>
                                            <Label htmlFor='repeat_password' className='font-bold text-sm'>Repetir senha</Label>
                                            <FormControl>
                                                <Input
                                                    type='password'
                                                    placeholder='Digite novamente sua senha'
                                                    className='rounded-md border-l-4 border-l-principal dark:bg-zinc-950'
                                                    {...field}
                                                />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />

                                <div className="flex w-full">
                                    <span className="text-xs">Já tem uma conta?
                                        <span onClick={() => router.push('/login')} className="text-xs text-principal animate-pulse cursor-pointer hover:underline hover:animate-none hover:color-second ml-1">
                                            Entrar agora
                                        </span>
                                    </span>
                                </div>
                                <div>
                                    <div className='flex w-full'>
                                        <Button
                                            type="submit"
                                            className='bg-principal mt-5 w-full text-white font-normal rounded-md shadow-md hover:bg-second'
                                            disabled={loading}
                                        >
                                            {loading ? (
                                                <Loader2 className="animate-spin h-5 w-5" />
                                            ) : (
                                                <div style={{ display: 'flex', alignItems: 'center' }}>
                                                    <span className="font-bold">Cadastrar</span>
                                                </div>
                                            )}
                                        </Button>
                                    </div>

                                </div>
                            </form>
                        </Form>
                    </div>
                </div>
            </div>
        </>
    );
}
