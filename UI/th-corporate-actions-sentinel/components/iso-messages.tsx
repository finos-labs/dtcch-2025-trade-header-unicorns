import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Badge } from "@/components/ui/badge"

const isoMessages = [
  { id: 1, type: "0100", description: "Authorization Request", timestamp: "2023-04-01T10:30:00Z" },
  { id: 2, type: "0110", description: "Authorization Response", timestamp: "2023-04-01T10:30:01Z" },
  { id: 3, type: "0200", description: "Financial Transaction Request", timestamp: "2023-04-01T10:35:00Z" },
  { id: 4, type: "0210", description: "Financial Transaction Response", timestamp: "2023-04-01T10:35:01Z" },
  { id: 5, type: "0400", description: "Reversal Request", timestamp: "2023-04-01T10:40:00Z" },
]

export function ISOMessages() {
  return (
    <Card className="h-[600px]">
      <CardHeader>
        <CardTitle>ISO Messages</CardTitle>
        <CardDescription>Recent ISO 8583 messages</CardDescription>
      </CardHeader>
      <CardContent>
        <ScrollArea className="h-[500px] pr-4">
          {isoMessages.map((message) => (
            <div key={message.id} className="mb-4 last:mb-0 flex items-center justify-between">
              <div>
                <Badge variant="secondary" className="mb-1">
                  {message.type}
                </Badge>
                <p className="text-sm font-medium">{message.description}</p>
                <p className="text-xs text-gray-500">{new Date(message.timestamp).toLocaleString()}</p>
              </div>
            </div>
          ))}
        </ScrollArea>
      </CardContent>
    </Card>
  )
}

