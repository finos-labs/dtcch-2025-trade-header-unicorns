import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { ScrollArea } from "@/components/ui/scroll-area"

const newsArticles = [
  {
    id: 1,
    title: "Global Economy Shows Signs of Recovery",
    summary: "Recent data suggests a positive trend in global economic indicators.",
  },
  {
    id: 2,
    title: "New Breakthrough in Renewable Energy",
    summary: "Scientists announce a major advancement in solar panel efficiency.",
  },
  {
    id: 3,
    title: "Tech Giants Announce Collaboration on AI Ethics",
    summary: "Leading tech companies join forces to establish AI governance standards.",
  },
  {
    id: 4,
    title: "Global Climate Summit Reaches Landmark Agreement",
    summary: "World leaders commit to ambitious carbon reduction targets.",
  },
  {
    id: 5,
    title: "Medical Research Paves Way for New Cancer Treatment",
    summary: "Promising results in clinical trials offer hope for cancer patients.",
  },
]

export function NewsFeed() {
  return (
    <Card className="h-[600px]">
      <CardHeader>
        <CardTitle>News Feed</CardTitle>
        <CardDescription>Latest articles from around the world</CardDescription>
      </CardHeader>
      <CardContent>
        <ScrollArea className="h-[500px] pr-4">
          {newsArticles.map((article) => (
            <div key={article.id} className="mb-4 last:mb-0">
              <h3 className="text-lg font-semibold">{article.title}</h3>
              <p className="text-sm text-gray-600">{article.summary}</p>
            </div>
          ))}
        </ScrollArea>
      </CardContent>
    </Card>
  )
}

