"use client"

import { useState } from "react"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { FileIcon, CheckIcon, FileTextIcon, PlayIcon } from "lucide-react"
import { NewsModal } from "./news-modal"
import { toast } from "@/components/ui/use-toast"

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
  },
]

export function NewsDashboard() {
  const [modalContent, setModalContent] = useState<{ type: "xml" | "json" | "text"; content: string } | null>(null)

  const openModal = (type: "xml" | "json" | "text", content: string) => {
    setModalContent({ type, content })
  }

  const receiveFeed = () => {
    // Simulate receiving a new feed
    toast({
      title: "Feed Received",
      description: "The news feed has been updated with the latest articles.",
    })
  }

  const executeAction = (id: number) => {
    // Simulate executing an action
    toast({
      title: "Action Executed",
      description: `Additional action executed for article ${id}.`,
    })
  }

  return (
    <>
      <Button onClick={receiveFeed} className="mb-4">
        Receive Feed
      </Button>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[40%]">Headline</TableHead>
            <TableHead>XML</TableHead>
            <TableHead>Check</TableHead>
            <TableHead>File</TableHead>
            <TableHead>Execute</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {newsArticles.map((article) => (
            <TableRow key={article.id} className="hover:bg-muted/50">
              <TableCell className="font-medium cursor-pointer" onClick={() => openModal("text", article.content)}>
                {article.headline}
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
                  <CheckIcon className="h-4 w-4" />
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
      {modalContent && (
        <NewsModal
          isOpen={!!modalContent}
          onClose={() => setModalContent(null)}
          content={modalContent.content}
          contentType={modalContent.type}
        />
      )}
    </>
  )
}

