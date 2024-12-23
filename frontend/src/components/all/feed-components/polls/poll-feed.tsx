import { useState } from "react";
import PollCard from "./poll-card";

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
  
  const PollFeed: React.FC = () => {
    const initialPolls: Poll[] = [
      {
        id: 1,
        question: "Qual é a sua linguagem de programação favorita?",
        options: ["JavaScript", "Python", "Java", "C#"],
        votes: [50, 30, 10, 5],
        totalVotes: 95,
        creator: {
          username: "developer123",
          avatarUrl: "https://via.placeholder.com/150",
          title: "Fullstack Developer",
        },
        createdAt: new Date(),
        isPro: false,
      },
      {
        id: 2,
        question: "Qual é o seu framework frontend favorito?",
        options: ["React", "Vue", "Angular", "Svelte"],
        votes: [60, 15, 10, 5],
        totalVotes: 90,
        creator: {
          username: "frontendguru",
          avatarUrl: "https://via.placeholder.com/150",
          title: "",
        },
        createdAt: new Date(),
        isPro: true, 
      },
      {
        id: 3,
        question: "Qual é o seu jogo favorito?",
        options: ["The Last of Us", "God of War", "Minecraft", "Fortnite"],
        votes: [40, 20, 25, 15],
        totalVotes: 100,
        creator: {
          username: "gamer123",
          avatarUrl: "https://via.placeholder.com/150",
          title: "Gamer",
        },
        createdAt: new Date(),
        isPro: true,
      },
    ];
  
    const [polls, setPolls] = useState<Poll[]>(initialPolls);
  
    const handleVote = (pollId: number, selectedOptionIndex: number | null) => {
      if (selectedOptionIndex === null) return;
  
      setPolls((prevPolls) =>
        prevPolls.map((poll) => {
          if (poll.id === pollId) {
            const updatedVotes = [...poll.votes];
            updatedVotes[selectedOptionIndex] += 1;
  
            return {
              ...poll,
              votes: updatedVotes,
              totalVotes: poll.totalVotes + 1,
            };
          }
          return poll;
        })
      );
    };
  
    return (
      <div className="space-y-8">
        {polls.map((poll) => (
          <PollCard key={poll.id} poll={poll} onVote={handleVote} />
        ))}
      </div>
    );
  };
  
  export default PollFeed;