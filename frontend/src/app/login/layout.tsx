
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Polls. | Login"
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