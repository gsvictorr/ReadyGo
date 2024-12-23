import { Card, CardContent, CardFooter, CardHeader } from "@/components/ui/card";
import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { formatDistanceToNowStrict } from "date-fns";
import { motion } from "framer-motion";
import { useEffect, useState } from "react";

interface Poll {
    id: number;
    question: string;
    options: string[];
    votes: number[];
    totalVotes: number;
    creator: {
        username: string;
        avatarUrl: string;
        title: string;
    };
    createdAt: Date;
    isPro?: boolean;
}

interface PollCardProps {
    poll: Poll;
    onVote: (pollId: number, selectedOptionIndex: number | null) => void;
}

const PollCard: React.FC<PollCardProps> = ({ poll, onVote }) => {
    const [selectedOption, setSelectedOption] = useState<number | null>(null);
    const [hasVoted, setHasVoted] = useState(false);
    const [timePassed, setTimePassed] = useState<string>("");
    const [particles, setParticles] = useState<{ id: number; x: number }[]>([]);
    useEffect(() => {
        const interval = setInterval(() => {
            const timeAgo = formatDistanceToNowStrict(poll.createdAt);
            setTimePassed(timeAgo);
        }, 1000);

        return () => clearInterval(interval);
    }, [poll.createdAt]);

    const calculateVotePercentages = () => {
        return poll.votes.map((voteCount) => {
            if (poll.totalVotes === 0) return 0;
            return ((voteCount / poll.totalVotes) * 100).toFixed(1);
        });
    };

    const votePercentages = calculateVotePercentages();

    const handleVote = () => {
        if (selectedOption !== null) {
            onVote(poll.id, selectedOption);
            setHasVoted(true);

            for (let i = 0; i < 15; i++) {
                const xPosition = Math.random() * 380;
                setParticles((prev) => [
                    ...prev,
                    { id: Date.now() + i, x: xPosition },
                ]);
            }
        }
    };

    return (
<motion.div
    initial={{ opacity: 0, scale: 0.95 }}
    animate={{ opacity: 1, scale:  1 }}
    transition={{ duration: 0.5 }}
    className="w-full md:max-w-md max-w-sm mx-auto"
>
    <Card className={`shadow-md md:hover:scale-105 md:hover:transition-all ease-in-out duration-500 ${poll.isPro ? 'border-2 rounded-xl border-dashed border-principal ' : ''}`}>
    <CardHeader className="flex items-start">
                    <div className="flex items-center space-x-2 ">
                        <Avatar className="md:h-12 md:w-12 h-10 w-10">
                            <AvatarImage src={poll.creator.avatarUrl} alt={`Avatar de ${poll.creator.username}`} />
                            <AvatarFallback>{poll.creator.username.charAt(0).toUpperCase()}</AvatarFallback>
                        </Avatar>
                        <div className="flex flex-col items-start">
                            <span className="font-semibold text-xs">
                                @{poll.creator.username}
                                {poll.isPro && (
                                    <span className="ml-1 text-principal text-xs font-bold">DELUXE</span>
                                )}
                            </span>
                            <span className="text-xs text-gray-500">{poll.creator.title === "" ? "Sem título" : poll.creator.title}</span>
                            <span className="text-xs text-gray-400">Postado há {timePassed}</span>
                        </div>
                    </div>
                </CardHeader>
        <CardContent>
            <h3 className="text-lg font-bold mb-2">{poll.question}</h3>
            <div className="space-y-2">
                {poll.options.map((option, index) => (
                    <div key={index} className="relative">
                        <Button
                            variant={selectedOption === index ? "default" : "outline"}
                            onClick={() => setSelectedOption(index)}
                            className={`relative z-10 w-full ${poll.isPro && selectedOption === index ? 'bg-principal hover:bg-second' : ''}`}
                            disabled={hasVoted}
                        >
                            {option}
                            {hasVoted && (
                                <div
                                    className="absolute inset-0 rounded-sm bg-second transition-all duration-300"
                                    style={{
                                        width: `${votePercentages[index]}%`,
                                        zIndex: 0,
                                        opacity: 0.4
                                    }}
                                />
                            )}
                        </Button>

                        {hasVoted && (
                            <span className="text-sm text-gray-500 mt-1">
                                {votePercentages[index]}% ({poll.votes[index]} votos)
                            </span>
                        )}
                    </div>
                ))}

                {poll.isPro && particles.map((particle) => (
                    <motion.div
                        key={particle.id}
                        className="absolute bg-principal h-3 w-3 rounded-full"
                        initial={{ opacity: 1, y: 0, x: particle.x }}
                        animate={{ opacity: 0, y: -155 }}
                        transition={{ duration: 2 }}
                        style={{ pointerEvents: "none" }}
                    />
                ))}
            </div>
        </CardContent>
        <CardFooter className="flex justify-between">
            <span className="text-sm text-gray-600">Total de votos: {poll.totalVotes}</span>
            <Button
                onClick={handleVote}
                disabled={selectedOption === null || hasVoted}
                className="btn-primary"
            >
                Votar agora
            </Button>
        </CardFooter>
    </Card>
</motion.div>
    );
};

export default PollCard;