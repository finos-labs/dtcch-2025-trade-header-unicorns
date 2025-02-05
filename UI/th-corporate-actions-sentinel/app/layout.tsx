import type { Metadata } from "next"
import { Inter } from "next/font/google"
import "./globals.css"
import type React from "react"
import { ToastProvider } from "@/components/ui/toast"
import { ThemeProvider } from "@/components/theme-provider"
import { Toaster } from "@/components/ui/toaster"

const inter = Inter({ subsets: ["latin"] })

export const metadata: Metadata = {
  title: "Corporate Actions Sentinel",
  description: "A dashboard displaying news articles about corporate actions, their transformation to ISO message and the matching your CDM trades to create an accepted workflow step",
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <ThemeProvider attribute="class" defaultTheme="system" enableSystem>
          <ToastProvider>
            <div className="flex min-h-screen flex-col">
              <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
                <div className="container flex h-14 items-center">
                  <div className="mr-4 hidden md:flex">
                    <a className="mr-6 flex items-center space-x-2" href="/">
                      <img
                        src="https://www.tradeheader.com/hs-fs/hubfs/tradeheader_logotip_web.png?width=135&height=85&name=tradeheader_logotip_web.png"
                        alt="Tradeheader Logo"
                        className="h-6 w-6"
                      />
                      <span className="hidden font-bold sm:inline-block">Tradeheader</span>
                    </a>
                  </div>
                </div>
              </header>
              <main className="flex-1">
                <div className="container py-6">{children}</div>
              </main>
              <footer className="border-t">
                <div className="container flex flex-col items-center justify-between gap-4 py-10 md:h-24 md:flex-row md:py-0">
                  <div className="flex flex-col items-center gap-4 px-8 md:flex-row md:gap-2 md:px-0">
                    <p className="text-center text-sm leading-loose text-muted-foreground md:text-left">
                      Built by TradeHeader-Unicorns.
                    </p>
                  </div>
                </div>
              </footer>
            </div>
          </ToastProvider>
        </ThemeProvider>
        <Toaster />
      </body>
    </html>
  )
}

