import type { Metadata } from "next";
import "./globals.css";
import { Roboto } from "next/font/google";
import { cn } from "@/lib/utils";


const roboto = Roboto({ weight: "500", subsets: ["latin"] });

export const metadata: Metadata = {
  title: "ReadyGo. Planeje sua viagem DOS SONHOS!",
  icons: {
    icon: ['/favicon.ico'],
    shortcut: ['/apple-touch-icon.png'],
    apple: ['/apple-touch-icon.png']
  },
  manifest: '/site.webmanifest'
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
       <body className={cn(roboto.className)}>
        {children}
      </body>
    </html>
  );
}
