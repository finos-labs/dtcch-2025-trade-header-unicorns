import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Badge } from "@/components/ui/badge"

interface NewsModalProps {
  isOpen: boolean
  onClose: () => void
  content: string
  contentType: "isoMessage" | "acceptedWorkflowStep" | "impactedOpenTrade" | "proposedEvent" | "json" | "text"
}

export function NewsModal({ isOpen, onClose, content, contentType }: NewsModalProps) {
  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[625px]">
        <DialogHeader>
          <DialogTitle className="flex items-center">
            {contentType === "text" ? "Article Details" : `${contentType.toUpperCase()} Content`}
            <Badge variant="outline" className="ml-2">
              {contentType === "text" ? "Full Article" : contentType.toUpperCase()}
            </Badge>
          </DialogTitle>
        </DialogHeader>
        <div className="mt-6">
          {contentType === "text" ? (
            <div className="prose prose-sm max-w-none">
              <p>{content}</p>
            </div>
          ) : (
            <pre className="bg-muted p-4 rounded-md overflow-auto max-h-[60vh] text-sm">
              <code>{content}</code>
            </pre>
          )}
        </div>
      </DialogContent>
    </Dialog>
  )
}

