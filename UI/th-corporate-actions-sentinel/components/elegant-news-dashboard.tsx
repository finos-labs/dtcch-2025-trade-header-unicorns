"use client"

import { useState } from "react"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { FileIcon, FileTextIcon, PlayIcon, RefreshCw } from "lucide-react"
import { NewsModal } from "./news-modal"
import { toast } from "@/components/ui/use-toast"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"

const newsArticles = [
  {
    id: 1,
    headline: "Global Economy Shows Signs of Recovery",
    content:
      "Recent data suggests a positive trend in global economic indicators. Analysts point to increased consumer spending and a rebound in manufacturing as key factors driving this recovery.",
    xml: "<news><headline>Global Economy Shows Signs of Recovery</headline><content>Recent data suggests a positive trend in global economic indicators.</content></news>",
    json: {
      headline: "Global Economy Shows Signs of Recovery",
      content: "Recent data suggests a positive trend in global economic indicators.",
    },
    category: "Economy",
  },
  {
    id: 2,
    headline: "New Breakthrough in Renewable Energy",
    content:
      "Scientists announce a major advancement in solar panel efficiency. The new technology could potentially double the amount of energy harvested from sunlight, making solar power more viable for widespread adoption.",
    xml: "<news><headline>New Breakthrough in Renewable Energy</headline><content>Scientists announce a major advancement in solar panel efficiency.</content></news>",
    json: {
      headline: "New Breakthrough in Renewable Energy",
      content: "Scientists announce a major advancement in solar panel efficiency.",
    },
    category: "Technology",
  },
  {
    id: 3,
    headline: "Tech Giants Announce Collaboration on AI Ethics",
    content:
      "Leading tech companies join forces to establish AI governance standards. This unprecedented collaboration aims to address concerns about AI bias, privacy, and transparency in machine learning algorithms.",
    xml: "<news><headline>Tech Giants Announce Collaboration on AI Ethics</headline><content>Leading tech companies join forces to establish AI governance standards.</content></news>",
    json: {
      headline: "Tech Giants Announce Collaboration on AI Ethics",
      content: "Leading tech companies join forces to establish AI governance standards.",
    },
    category: "Technology",
  },
  {
    id: 4,
    headline: "Global Climate Summit Reaches Landmark Agreement",
    content:
      "World leaders commit to ambitious carbon reduction targets. The agreement includes a pledge to achieve net-zero emissions by 2050 and significant increases in funding for climate adaptation in developing countries.",
    xml: "<news><headline>Global Climate Summit Reaches Landmark Agreement</headline><content>World leaders commit to ambitious carbon reduction targets.</content></news>",
    json: {
      headline: "Global Climate Summit Reaches Landmark Agreement",
      content: "World leaders commit to ambitious carbon reduction targets.",
    },
    category: "Environment",
  },
  {
    id: 5,
    headline: "Medical Research Paves Way for New Cancer Treatment",
    content:
      "Promising results in clinical trials offer hope for cancer patients. The new treatment combines immunotherapy with targeted gene editing, showing remarkable success in treating previously resistant forms of cancer.",
    xml: "<news><headline>Medical Research Paves Way for New Cancer Treatment</headline><content>Promising results in clinical trials offer hope for cancer patients.</content></news>",
    json: {
      headline: "Medical Research Paves Way for New Cancer Treatment",
      content: "Promising results in clinical trials offer hope for cancer patients.",
    },
    category: "Health",
  },
]

export function ElegantNewsDashboard() {
  const [modalContent, setModalContent] = useState<{ type: "xml" | "json" | "text"; content: string } | null>(null)

  const openModal = (type: "xml" | "json" | "text", content: string) => {
    setModalContent({ type, content })
  }

  const receiveFeed = () => {
    toast({
      title: "Feed Updated",
      description: "The news feed has been refreshed with the latest articles.",
    })
  }

  const executeAction = (id: number) => {
    toast({
      title: "Action Executed",
      description: `Additional action executed for article ${id}.`,
    })
  }

  return (
    <Card className="animate-in">
      <CardHeader>
        <div className="flex items-center justify-between">
          <div>
            <CardTitle className="text-2xl">Corporate Actions Sentinel</CardTitle>
            <CardDescription>Latest curated news articles from around the world</CardDescription>
          </div>
          <Button onClick={receiveFeed} className="ml-auto">
            <RefreshCw className="mr-2 h-4 w-4" />
            Refresh Feed
          </Button>
        </div>
      </CardHeader>
      <CardContent>
        <div className="rounded-md border">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="w-[40%]">Headline</TableHead>
                <TableHead>Category</TableHead>
                <TableHead>XML</TableHead>
                <TableHead>JSON</TableHead>
                <TableHead>Actions</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {newsArticles.map((article) => (
                <TableRow key={article.id} className="group">
                  <TableCell
                    className="font-medium cursor-pointer transition-colors hover:text-primary"
                    onClick={() => openModal("text", article.content)}
                  >
                    {article.headline}
                  </TableCell>
                  <TableCell>
                    <Badge variant="secondary">{article.category}</Badge>
                  </TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm" onClick={() => openModal("xml", article.xml)}>
                      <FileTextIcon className="h-4 w-4" />
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button
                      variant="ghost"
                      size="sm"
                      onClick={() => openModal("json", JSON.stringify(article.json, null, 2))}
                    >
                      <FileIcon className="h-4 w-4" />
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm" onClick={() => executeAction(article.id)}>
                      <PlayIcon className="h-4 w-4" />
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </CardContent>
      {modalContent && (
        <NewsModal
          isOpen={!!modalContent}
          onClose={() => setModalContent(null)}
          content={modalContent.content}
          contentType={modalContent.type}
        />
      )}
    </Card>
  )
}

