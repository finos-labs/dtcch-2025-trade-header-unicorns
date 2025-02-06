import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { DialogFooter } from "@/components/ui/dialog"

interface NewsModalProps {
  isOpen: boolean
  onClose: () => void
  content: string
  contentType: "isoMessage" | "acceptedWorkflowStep" | "impactedOpenTrade" | "proposedEvent" | "json" | "text"
}

export function NewsModal({ isOpen, onClose, content, contentType }: NewsModalProps) {
  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="max-w-3xl max-h-[80vh] overflow-hidden">
        <DialogHeader>
          <DialogTitle className="flex items-center">
            {contentType === "text" ? "Article Details" : `${contentType.toUpperCase()} Content`}
            <Badge variant="outline" className="ml-2">
              {contentType === "text" ? "Full Article" : contentType.toUpperCase()}
            </Badge>
          </DialogTitle>
        </DialogHeader>
        <div className="overflow-y-auto max-h-[calc(80vh-8rem)]">
          {contentType === "text" ? (
            <p className="text-sm">{content}</p>
          ) : (
            <pre className="text-sm bg-muted p-4 rounded-md overflow-x-auto">
              <code>{content}</code>
            </pre>
          )}
        </div>
        <DialogFooter>
          <Button onClick={onClose}>Close</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  )
}

