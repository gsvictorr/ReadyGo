
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Tudo na Mala | Login"
};

export default function LoginLayout({ children }: { children: React.ReactNode }) {
    return (
        <>
            <div className="">
                {children}
            </div>
        </>
    )
}