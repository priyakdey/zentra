import { Button } from "@/components/ui/button.tsx";
import { Calendar } from "@/components/ui/calendar.tsx";
import {
  Popover,
  PopoverContent,
  PopoverTrigger
} from "@/components/ui/popover.tsx";
import { cn } from "@/lib/utils.ts";
import { format } from "date-fns";
import { CalendarIcon } from "lucide-react";
import { useState } from "react";

type SingleDatePickerPropsType = {
  value: Date | null,
  onChange: (date: Date | null) => void
};

function SingleDatePicker({ value, onChange }: SingleDatePickerPropsType) {
  const [ open, setOpen ] = useState(false);

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          className={cn(
            "w-full justify-start text-left font-normal",
            !value && "text-muted-foreground"
          )}
        >
          <CalendarIcon className="mr-2 h-4 w-4" />
          {value ? format(value, "PPP") : <span>Pick a date</span>}
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-auto p-0">
        <Calendar
          mode="single"
          selected={value!}
          onSelect={(selectedDate: Date | undefined) => {
            onChange(selectedDate ?? null);
            setOpen(false);
          }}
          initialFocus
        />
      </PopoverContent>
    </Popover>
  );
}

export default SingleDatePicker;