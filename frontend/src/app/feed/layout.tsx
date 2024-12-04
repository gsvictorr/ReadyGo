
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Polls. | Feed"
};

export default function FeedLayout({ children }: { children: React.ReactNode }) {
    return (
        <>
            <div className="">
                {children}
            </div>
        </>
    )
}