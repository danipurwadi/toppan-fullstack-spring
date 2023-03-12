import React from 'react';
import { rest } from 'msw';
import { setupServer } from 'msw/node';

import { render, fireEvent, screen } from '@testing-library/react';
import App from './App';


const server = setupServer(
  rest.get('http://localhost:8080/getRandomCountry', (req, res, ctx) => {
    return res(ctx.json({
      country: {
        full_name: "Singapore",
        country_code: "SG"
      }
    }));
  }),
  rest.get('http://localhost:8080/getTop3ReadBooks', (req, res, ctx) => {
    return res(ctx.json([
      {
        name: "Harry Potter",
        author: "JK Rowling",
        borrower: [
          "Potter Doe",
          "Potter Smith",
          "Potter Lee"
        ]
      },
      {
        name: "Lord of the Rings",
        author: "JRR Tolkein",
        borrower: [
          "Lord Doe",
          "Lord Smith",
          "Lord Lee"
        ]
      },
      {
        name: "The Hobbit",
        author: "JRR Tolkein",
        borrower: [
          "Hobbit Doe",
          "Hobbit Smith",
          "Hobbit Lee"
        ]
      }
    ]));
  })
);

// establish API mocking before all tests
beforeAll(() => server.listen());
// reset any request handlers that are declared as a part of our tests
// (i.e. for testing one-time error scenarios)
afterEach(() => server.resetHandlers());
// clean up once the tests are done
afterAll(() => server.close());

test('loads and display data correctly', async () => {
  render(<App />);
  expect(screen.getByTestId('action-btn')).toHaveTextContent("Get country:");
  expect(screen.getByTestId('error-message')).toHaveTextContent("No data found");

  fireEvent.click(screen.getByTestId('action-btn'));
  await screen.findByTestId('book-item-1');

  expect(screen.getByTestId('action-btn')).toHaveTextContent("Get country: SG");
  expect(screen.getByTestId('book-item-1')).toHaveTextContent("1 Harry Potterarrow.svgby JK Rowling");
  expect(screen.getByTestId('book-item-2')).toHaveTextContent("2 Lord of the Ringsarrow.svgby JRR Tolkein");
  expect(screen.getByTestId('book-item-3')).toHaveTextContent("3 The Hobbitarrow.svgby JRR Tolkein");

  fireEvent.click(screen.getAllByTestId('book-toggle')[0]);
  await screen.findAllByTestId('customer');
  expect(screen.getAllByTestId('customer')[0]).toHaveTextContent("Potter Doe");
  expect(screen.getAllByTestId('customer')[1]).toHaveTextContent("Potter Smith");
  expect(screen.getAllByTestId('customer')[2]).toHaveTextContent("Potter Lee");

  fireEvent.click(screen.getAllByTestId('book-toggle')[1]);
  await screen.findAllByTestId('customer');
  expect(screen.getAllByTestId('customer')[0]).toHaveTextContent("Lord Doe");
  expect(screen.getAllByTestId('customer')[1]).toHaveTextContent("Lord Smith");
  expect(screen.getAllByTestId('customer')[2]).toHaveTextContent("Lord Lee");
  // ensure previous accordion is closed
  expect(screen.queryAllByText('Potter Lee')).toHaveLength(0);

  fireEvent.click(screen.getAllByTestId('book-toggle')[2]);
  await screen.findAllByTestId('customer');
  expect(screen.getAllByTestId('customer')[0]).toHaveTextContent("Hobbit Doe");
  expect(screen.getAllByTestId('customer')[1]).toHaveTextContent("Hobbit Smith");
  expect(screen.getAllByTestId('customer')[2]).toHaveTextContent("Hobbit Lee");
  expect(screen.queryAllByText('Lord Doe')).toHaveLength(0);
});


test('display no data found when server returns no results', async () => {
  server.use(
    rest.get('http://localhost:8080/getTop3ReadBooks', (req, res, ctx) => {
      return res(ctx.json({
        message: "no results"
      }));
    })
  );

  render(<App />);
  expect(screen.getByTestId('action-btn')).toHaveTextContent("Get country:");
  expect(screen.getByTestId('error-message')).toHaveTextContent("No data found");

  fireEvent.click(screen.getByTestId('action-btn'));
  await screen.findByTestId('error-message');
  expect(screen.getByTestId('error-message')).toHaveTextContent("No data found");
});

test('display no data found when server returns invalid parameter', async () => {
  server.use(
    rest.get('http://localhost:8080/getTop3ReadBooks', (req, res, ctx) => {
      return res(ctx.json({
        message: "invalid paramter"
      }));
    })
  );

  render(<App />);
  expect(screen.getByTestId('action-btn')).toHaveTextContent("Get country:");
  expect(screen.getByTestId('error-message')).toHaveTextContent("No data found");

  fireEvent.click(screen.getByTestId('action-btn'));
  await screen.findByTestId('error-message');
  expect(screen.getByTestId('error-message')).toHaveTextContent("No data found");
});
