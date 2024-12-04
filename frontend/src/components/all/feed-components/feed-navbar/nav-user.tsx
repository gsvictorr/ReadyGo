"use client";

import { Avatar } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { DropdownMenuContent, DropdownMenuGroup, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator } from "@/components/ui/dropdown-menu";
import { AvatarFallback, AvatarImage } from "@radix-ui/react-avatar";
import { DropdownMenu, DropdownMenuTrigger } from "@radix-ui/react-dropdown-menu";
import { BadgeCheck, Bell, ChevronsUpDown, CircleUser, LogOut, Sparkles } from "lucide-react";


interface User {
  user: {
    name: string;
    email: string;
    avatar: string;
    isPro?: boolean;
  }
}

export function NavUser({ user }: User) {

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild >
        <Button className="bg-white shadow-none hover:bg-gray-200 py-3">
          <Avatar className="h-8 w-8 rounded-lg">
            <AvatarImage src={user.avatar} alt={user.name} />
            <AvatarFallback className="rounded-lg">VS</AvatarFallback>
          </Avatar>
          <div className="grid flex-1 text-left text-sm leading-tight">
            <span className="text-black font-bold text-xs">{user.name}
              {user.isPro && (
                <span className="ml-1 text-principal text-xs font-bold">DELUXE</span>
              )}
            </span>
            <span className="text-black text-xs hidden md:block">{user.email}</span>
          </div>
          <ChevronsUpDown className="ml-auto size-4 text-black" />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent
        className="w-[--radix-dropdown-menu-trigger-width] min-w-56 rounded-lg"
        align="end"
        sideOffset={4}
      >
        <DropdownMenuLabel className="p-0 font-normal">
          <div className="flex items-center gap-2 px-1 py-1.5 text-left text-sm">
            <Avatar className="h-8 w-8 rounded-lg">
              <AvatarImage src={user.avatar} alt={user.name} />
              <AvatarFallback className="rounded-lg">VS</AvatarFallback>
            </Avatar>
            <div className="grid flex-1 text-left text-sm leading-tight">
              <span className="truncate font-semibold">{user.name}
                {user.isPro && (
                  <span className="ml-1 text-principal text-xs font-bold">DELUXE</span>
                )}
              </span>
              <span className="truncate text-xs">{user.email}</span>
            </div>
          </div>
        </DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuGroup>
          <DropdownMenuItem>
            {user.isPro ? <div className="flex">
              <BadgeCheck size={16} className="mr-2" />
              <span>Você já é
                <span className="text-principal font-bold ml-1">
                   DELUXE
                </span>.
              </span>

            </div> :
              <div className="flex">
                <Sparkles size={16} className="mr-2" />
                <span>Upgrade para
                  <span className="text-principal font-bold ml-1">
                    DELUXE
                  </span>
                  .</span>

              </div>
            }
          </DropdownMenuItem>
          <DropdownMenuSeparator />
          <DropdownMenuItem>
            <CircleUser />
            Perfil
          </DropdownMenuItem>
          <DropdownMenuItem>
            <Bell />
            Notificações
          </DropdownMenuItem>
        </DropdownMenuGroup>
        <DropdownMenuSeparator />
        <DropdownMenuItem className="cursor-pointer">
          <LogOut />
          Log out
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  )
}