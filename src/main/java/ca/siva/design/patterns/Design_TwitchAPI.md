
Design The Twitch API
Design every API endpoint that's interacted with when a user is on an individual Twitch streamer's channel page, watching their livestream.

Many systems design questions are intentionally left very vague and are literally given in the form of `Design Foobar`. It's your job to ask clarifying questions to better understand the system that you have to build.

We've laid out some of these questions below; their answers should give you some guidance on the problem. Before looking at them, we encourage you to take few minutes to think about what questions you'd ask in a real interview.

====================================================================

1. Gathering Requirements
As with any API design interview question, the first thing that we want to do is to gather API requirements; we need to figure out what API we're building exactly.
We're designing every API endpoint that's interacted with when a user is on an individual Twitch streamer's channel page, watching their livestream. Specifically, we need to handle:
displaying the streamer's channel info (description text, follower count, etc.)
following and unfollowing the streamer
subscribing to and unsubscribing from the streamer
seeing the live chat and sending messages; sending messages should only be allowable if the user isn't banned
seeing the livestream and being able to pause / unpause it
seeing the number of concurrent viewers of the stream, which should automatically be updated every 30 seconds or so

2. Coming Up With A Plan
It's important to organize ourselves and to lay out a clear plan regarding how we're going to tackle our design. What are the major, potentially contentious parts of our API? Why are we making certain design decisions?
Fortunately for us, the various functionalities that we have to support effectively lay out a step-by-step plan for us, so we'll simply follow that.
Of note, all of the API endpoints that we'll define will take in, by default, the caller's user-specific authentication token as an authorization header. This token will be used by the backend to identify which user is calling each API endpoint.
We'll also be passing a channelId as a parameter to all of the endpoints, which will be the unique username of the streamer in question.

3. Channel Info
This is the most straightforward piece of functionality on the page, since it only consists of displaying static data about the streamer.
The user will call the GetChannelInfo endpoint, which will return the relevant entity, ChannelInfo, to be displayed on the page.
ChannelInfo
name: string
description: string
currentStreamTitle: string
followerCount: int
This entity might have more fields, but these are the most important ones.
GetChannelInfo(channelId: string)
  => ChannelInfo
4. Following
The follow status is binary: either the user is following the streamer, or they aren't. Thus, we can support the follow functionality with a single endpoint that uses a toggle mechanism, whereby the backend sets the follow status to the opposite of what's currently stored in the database.
ToggleFollow(channelId: string)
  => FollowState (FOLLOWING or NOT_FOLLOWING)
Naturally, this endpoint will be called when the user presses the "Follow" / "Unfollow" button.
Note that we haven't yet handled how to know what the user's follow state is. In other words, how do we know whether to show "Follow" or "Unfollow" to the user? See the Relationship To Channel section for details.

5. Subscribing
Subscribing is similar to following. However, unlike following, since there are more details to be provided when a user subscribes to a channel (subscription tier and payment information), we'll separate the acts of subscribing and unsubscribing into two endpoints.
CreateSubscription(channelId: string, subscriptionInfo: SubscriptionInfo, paymentInfo: PaymentInfo)
  => Subscription

CancelSubscription(channelId: string)
  => Subscription
Naturally, these endpoints will be called when the user presses the "Subscribe" / "Unsubscribe" button.
Note that we haven't yet handled how to know what the user's subscription state is. In other words, how do we know whether to show "Subscribe" or "Unsubscribe" to the user? See the Relationship To Channel section for details.

6. Chat
To handle the chat's functionality, we'll need two endpoints and a Message entity.
Message
sender: string, the username of the sender
text: string
timestamp: string, in ISO format
StreamChat(channelId: string)
  => Message

SendMessage(channelId: string, message: string)
  => string | Error if user is banned
The StreamChat endpoint streams the stream's chat messages over a long-lived websocket connection and will be called once on page load.
The SendMessage endpoint will naturally be called whenever the user sends a message, and we can have the backend take care of timestamping messages and providing both the sender and the timestamp on the Message entity.
We can handle Twitch emotes by representing them with a special string format, like wrapping unique emote IDs in colons, as follows: :emote-id:. A Twitch a message will therefore look like this in string format:
"This stream is so fun to watch :kappa:"
The UI knows to detect this special string format and to display emotes appropriately. The UI also knows not to display messages sent by the user in question and received via StreamChat, since those messages will be displayed as soon as the user sends them via SendMessage.
While SendMessage returns an error if the user is banned from the chat, we won't actually allow the user to hit this endpoint if they're banned. That being said, we haven't yet handled how to know whether a user is banned. See the Relationship To Channel section for details.


7. Video
To display the actual video of the livestream, we'll open another long-lived websocket connection on page load, which will stream the video.
StreamVideo(channelId: string, videoQuality: VideoQuality)
  => VideoInfo
When this endpoint is called, we can imagine that the backend increases the concurrent-viewer count of the relevant stream in some database, which will be used in the next section to display the number of concurrent viewers to the user. When the long-lived connection is terminated (on tab close or page leave), the backend will decrease the relevant concurrent-viewer count in the database.
Lastly, when the user pauses the video, the UI still streams the video, but it simply doesn't display it.


8. Concurrent Viewers
Displaying the number of concurrent viewers watching a stream at any given time can easily be accomplished by polling an endpoint every 30 seconds or so, which reads from the database that stores every stream's concurrent-viewer count.
GetConcurrentViewers(channelId: string)
  => int

9. Relationship To Channel
There are a few pieces of functionality on the page that have to do with the relationship between the user and the streamer. Namely, whether the user is following the streamer, whether they're subscribed to the streamer, and whether they're banned from the streamer's chat.
One way to handle the follow and subscription states would be to fetch the user's profile info, which could contain all of their followed and subscribed streamers. This would be used with the streamer's name from GetChannelInfo to display the correct states (buttons) on the UI. The only problem is that a user could theoretically be following / subscribed to thousands of streamers, so we would maybe want to paginate the lists of followed and subscribed streamers, which would complicate things.
To make things simpler, and since we also have to handle the banned state, we can rely on a GetRelationshipToChannel endpoint, which will return the relevant entity, RelationshipToChannel, to be used to display the correct states on the page.
RelationshipToChannel
isBanned: boolean
isFollowing: boolean
subscription: Subscription | null
GetRelationshipToChannel(channelId: string)
  => RelationshipToChannel
If the user is banned, we'll prevent them from sending chat messages (and calling the SendMessage endpoint) altogether.



