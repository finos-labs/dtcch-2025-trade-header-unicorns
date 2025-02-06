"use client"

import { useState } from "react"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { FileIcon, FileTextIcon, PlayIcon, RefreshCw } from "lucide-react"
import { NewsModal } from "./news-modal"
import { toast } from "@/components/ui/use-toast"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { newsArticles } from "@/data/news-articles"

export function ElegantNewsDashboard() {
  const [modalContent, setModalContent] = useState<{
    type: "isoMessage" | "acceptedWorkflowStep" | "impactedOpenTrade" | "proposedEvent" | "json" | "text"
    content: string
    isoValidation?: string
  } | null>(null)

  const openModal = (
    type: "isoMessage" | "acceptedWorkflowStep" | "impactedOpenTrade" | "proposedEvent" | "json" | "text",
    content: string,
    isoValidation?: string
  ) => {
    setModalContent({ type, content, isoValidation })
  }

  const receiveFeed = () => {
    toast({
      title: "Feed Updated",
      description: "The news feed has been refreshed with the latest articles.",
    })
  }

  const executeAction = (article: typeof newsArticles[0]) => {
    openModal("json", JSON.stringify(article.acceptedWorkflowStep, null, 2))
    toast({
      title: "Action Executed",
      description: `Additional action executed for article ${article.id}.`,
    })
  }

  return (
    <Card className="animate-in">
      <CardHeader>
        <div className="flex items-center justify-between">
          <div>
            <CardTitle className="text-2xl">Corporate Actions Sentinel</CardTitle>
            <CardDescription>Update your CDM Trades library from the latest news articles</CardDescription>
          </div>
          <Button onClick={receiveFeed} className="ml-auto">
            <RefreshCw className="mr-2 h-4 w-4" />
            Refresh News Feed
          </Button>
        </div>
      </CardHeader>
      <CardContent>
        <div className="rounded-md border">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="w-[40%]">Headline</TableHead>
                <TableHead>ISO Message</TableHead>
                <TableHead>Impacted Open Trade</TableHead>
                <TableHead>CDM Proposed Event</TableHead>
                <TableHead>Generate CDM Accepted Event</TableHead>
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
                    <Button 
                      variant="ghost" 
                      size="sm" 
                      onClick={() => openModal(
                        "isoMessage", 
                        article.isoMessage, 
                        JSON.stringify(article.isoValidation, null, 2)
                      )}
                    >
                      <FileTextIcon className="h-4 w-4 text-green-700" />
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm" onClick={() => openModal("impactedOpenTrade", JSON.stringify(article.impactedOpenTrade, null, 2))}>
                      <Badge variant="secondary" className="bg-yellow-100 hover:bg-yellow-200 text-yellow-900 border-yellow-200">
                        {article.impactedTradeName}
                      </Badge>
                    </Button>
                  </TableCell>
                  <TableCell>

                    <Button
                      variant="ghost"
                      size="sm"
                      onClick={() => openModal("proposedEvent", JSON.stringify(article.proposedEvent, null, 2))}
                    >
                      <FileIcon className="h-4 w-4" />
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm" onClick={() => openModal("acceptedWorkflowStep", JSON.stringify(article.acceptedWorkflowStep, null, 2))}>
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
          isoValidation={modalContent.isoValidation}
        />
      )}
    </Card>
  )
}

