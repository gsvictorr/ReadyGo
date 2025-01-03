
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Tudo na Mala | Dashboard"
};

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
    return (
        <>
            <div className="">
                {children}
            </div>
        </>
    )
}