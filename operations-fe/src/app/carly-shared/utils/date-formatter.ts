import {MatDateFormats, DateAdapter} from "@angular/material";
import * as moment from 'moment';
import Moment = moment.Moment;

export const DATE_FORMAT_ISO_8601 = 'YYYY-MM-DD';
export const DATE_TIME_FORMAT_ISO_8601 = 'YYYY-MM-DD HH:mm:ss';

export const CARLY_DATE_FORMATS: MatDateFormats = {
  parse: {
    dateInput: {month: 'numeric', year: 'numeric', day: 'numeric'},
  },
  display: {
    dateInput: {year: 'short', month: 'long', day: 'narrow'},
    monthYearLabel: {year: 'numeric', month: 'short'},
    dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
    monthYearA11yLabel: {year: 'numeric', month: 'long'},
  }
};

export class CarlyDateAdapter extends DateAdapter<Moment> {
  /**
   * Gets the year component of the given date.
   * @param date The date to extract the year from.
   * @returns The year component.
   */
  getYear(date: Moment): number {
    return date.year();
  }
  /**
   * Gets the month component of the given date.
   * @param date The date to extract the month from.
   * @returns The month component (0-indexed, 0 = January).
   */
  getMonth(date: Moment): number {
    return date.month();
  }
  /**
   * Gets the date of the month component of the given date.
   * @param date The date to extract the date of the month from.
   * @returns The month component (1-indexed, 1 = first of month).
   */
  getDate(date: Moment): number {
    return date.date();
  }
  /**
   * Gets the day of the week component of the given date.
   * @param date The date to extract the day of the week from.
   * @returns The month component (0-indexed, 0 = Sunday).
   */
  getDayOfWeek(date: Moment): number {
    return date.day();
  }
  /**
   * Gets a list of names for the months.
   * @param style The naming style (e.g. long = 'January', short = 'Jan', narrow = 'J').
   * @returns An ordered list of all month names, starting with January.
   */
  getMonthNames(style: 'long' | 'short' | 'narrow'): string[] {
    switch (style) {
      case 'long':
        return moment.months();
      case 'short':
        return moment.monthsShort();
      case 'narrow':
        return moment.monthsShort().map(month => month.charAt(0));
      default:
        return moment.monthsShort();
    }
  }
  /**
   * Gets a list of names for the dates of the month.
   * @returns An ordered list of all date of the month names, starting with '1'.
   */
  getDateNames(): string[] {
    return new Array(31).fill(1).map((val, i) => String(val + i));
  }
  /**
   * Gets a list of names for the days of the week.
   * @param style The naming style (e.g. long = 'Sunday', short = 'Sun', narrow = 'S').
   * @returns An ordered list of all weekday names, starting with Sunday.
   */
  getDayOfWeekNames(style: 'long' | 'short' | 'narrow'): string[] {
    switch (style) {
      case 'long':
        return moment.weekdays();
      case 'short':
        return moment.weekdaysShort();
      case 'narrow':
        return moment.weekdaysMin();
      default:
        return moment.weekdaysShort();
    }
  }
  /**
   * Gets the name for the year of the given date.
   * @param date The date to get the year name for.
   * @returns The name of the given year (e.g. '2017').
   */
  getYearName(date: Moment): string {
    return String(date.year());
  }
  /**
   * Gets the first day of the week.
   * @returns The first day of the week (0-indexed, 0 = Sunday).
   */
  getFirstDayOfWeek(): number {
    return 1;
  }
  /**
   * Gets the number of days in the month of the given date.
   * @param date The date whose month should be checked.
   * @returns The number of days in the month of the given date.
   */
  getNumDaysInMonth(date: Moment): number {
    return date.daysInMonth();
  }
  /**
   * Clones the given date.
   * @param date The date to clone
   * @returns A new date equal to the given date.
   */
  clone(date: Moment): Moment {
    return date.clone();
  }
  /**
   * Creates a date with the given year, month, and date. Does not allow over/under-flow of the
   * month and date.
   * @param year The full year of the date. (e.g. 89 means the year 89, not the year 1989).
   * @param month The month of the date (0-indexed, 0 = January). Must be an integer 0 - 11.
   * @param date The date of month of the date. Must be an integer 1 - length of the given month.
   * @returns The new date, or null if invalid.
   */
  createDate(year: number, month: number, date: number): Moment {
    const d = moment(`${year}-${month+1}-${date}`, 'YYYY-MM-DD');
    return d.isValid()? d : null;
  }
  /**
   * Gets today's date.
   * @returns Today's date.
   */
  today(): Moment {
    return moment();
  }
  /**
   * Adds the given number of years to the date. Years are counted as if flipping 12 pages on the
   * calendar for each year and then finding the closest date in the new month. For example when
   * adding 1 year to Feb 29, 2016, the resulting date will be Feb 28, 2017.
   * @param date The date to add years to.
   * @param years The number of years to add (may be negative).
   * @returns A new date equal to the given one with the specified number of years added.
   */
  addCalendarYears(date: Moment, years: number): Moment {
    return date.clone().add(years, 'years');
  }
  /**
   * Adds the given number of months to the date. Months are counted as if flipping a page on the
   * calendar for each month and then finding the closest date in the new month. For example when
   * adding 1 month to Jan 31, 2017, the resulting date will be Feb 28, 2017.
   * @param date The date to add months to.
   * @param months The number of months to add (may be negative).
   * @returns A new date equal to the given one with the specified number of months added.
   */
  addCalendarMonths(date: Moment, months: number): Moment {
    return date.clone().add(months, 'months');
  }
  /**
   * Adds the given number of days to the date. Days are counted as if moving one cell on the
   * calendar for each day.
   * @param date The date to add days to.
   * @param days The number of days to add (may be negative).
   * @returns A new date equal to the given one with the specified number of days added.
   */
  addCalendarDays(date: Moment, days: number): Moment {
    return date.clone().add(days, 'days');
  }
  /**
   * Gets the RFC 3339 compatible string (https://tools.ietf.org/html/rfc3339) for the given date.
   * This method is used to generate date strings that are compatible with native HTML attributes
   * such as the `min` or `max` attribute of an `<input>`.
   * @param date The date to get the ISO date string for.
   * @returns The ISO date string date string.
   */
  toIso8601(date: Moment): string {
    return date.toISOString();
  }
  /**
   * Checks whether the given object is considered a date instance by this DateAdapter.
   * @param obj The object to check
   * @returns Whether the object is a date instance.
   */
  isDateInstance(obj: any): boolean {
    return obj instanceof moment;
  }
  /**
   * Checks whether the given date is valid.
   * @param date The date to check.
   * @returns Whether the date is valid.
   */
  isValid(date: Moment): boolean {
    return date.isValid();
  }
  /**
   * Gets date instance that is not valid.
   * @returns An invalid date.
   */
  invalid(): Moment {
    return null;
  };

  format(date: Moment, displayFormat: Object): string {
    return date.format(DATE_FORMAT_ISO_8601);
  }

  parse(value: any, parseFormat: any): Moment {
    return moment(value, DATE_FORMAT_ISO_8601);
  }
}
