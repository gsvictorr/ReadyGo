'use client';

import { FeedNavBar } from "@/components/all/feed-components/feed-navbar";
import PollFeed from "@/components/all/feed-components/polls/poll-feed";


export default function Feed() {
    return (
        <>
              <FeedNavBar/>
              <PollFeed />
        </>
    );
}